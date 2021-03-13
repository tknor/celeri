package io.celeri

import io.celeri.nok.config.NotificationsConfig
import io.celeri.nok.dao.emailNotificationEntityMapper
import io.celeri.nok.dao.entity.ReportStateEntity
import io.celeri.nok.dao.entity.WatchEntity
import io.celeri.nok.dao.repo.EmailNotificationRepo
import io.celeri.nok.dao.repo.ReportStateRepo
import io.celeri.nok.dao.repo.SmsNotificationRepo
import io.celeri.nok.dao.repo.WatchRepo
import io.celeri.nok.dao.smsNotificationEntityMapper
import io.celeri.nok.domain.NokCheck
import io.celeri.nok.domain.NokChecker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant

@Component
class AfterStartup(
        val notificationsConfig: NotificationsConfig,
        val watchRepo: WatchRepo,
        val emailNotificationRepo: EmailNotificationRepo,
        val smsNotificationRepo: SmsNotificationRepo,
        val reportStateRepo: ReportStateRepo) {

    val defaultId = "default"

    @EventListener(ApplicationReadyEvent::class)
    fun afterStartup() {

        val watchEntity = watchRepo.save(WatchEntity(defaultId, Instant.now()))

        notificationsConfig.emailNotifications.forEach {
            emailNotificationRepo.save(emailNotificationEntityMapper(watchEntity, it))
        }

        notificationsConfig.smsNotifications.forEach {
            smsNotificationRepo.save(smsNotificationEntityMapper(watchEntity, it))
        }

        // TODO no configuration yet
        reportStateRepo.save(ReportStateEntity(
                defaultId,
                Instant.now(),
                Instant.now(),
                Duration.ofHours(48).toMillis(),
                Duration.ofDays(14).toMillis()))

        NokChecker(
                Duration.ofSeconds(5).toMillis(),
                NokCheck(reportState, watch))
                    .start()
    }
}