package io.celeri.nok

import io.celeri.common.minutesToMillis
import io.celeri.nok.config.NotificationsConfig
import io.celeri.nok.domain.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope
import java.nio.file.Paths
import java.time.Duration
import java.time.Instant

@Configuration
class NokConfig(
        private val notificationsConfig: NotificationsConfig,
        private val emailSender: EmailSender,
        private val smsSender: SmsSender) {

    @Bean
    @Scope("singleton")
    fun nok(): Nok {

        val notifications = ArrayList<Notification>()

        notificationsConfig.emailNotifications.forEach {
            notifications.add(EmailNotification(
                    EmailNotificationRecipient(it.recipientAddress),
                    it.emailSubject,
                    Paths.get(it.emailMessageResourcePath),
                    minutesToMillis(it.heartbeatToTriggerMinutes),
                    Instant.now(),
                    emailSender))
        }

        notificationsConfig.smsNotifications.forEach {
            notifications.add(SmsNotification(
                    SmsNotificationRecipient(it.recipientPhoneNumber),
                    Paths.get(it.smsMessageResourcePath),
                    minutesToMillis(it.heartbeatToTriggerMinutes),
                    Instant.now(),
                    smsSender))
        }

        val watch = Watch(Instant.now(), notifications)

        // TODO create ReportConfig

        val reportState = ReportState(
                Instant.now(),
                Instant.now(),
                Duration.ofHours(48).toMillis(),
                Duration.ofDays(14).toMillis(),
                emailSender,
                smsSender)

        return Nok(watch, reportState)
    }
}