package io.celeri.nok.config

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "celeri.notifications")
class NotificationsConfig(
        val emailNotifications: List<EmailNotificationConfig>,
        val smsNotifications: List<SmsNotificationConfig>
)