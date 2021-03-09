package io.celeri.nok.domain

import io.celeri.nok.hoursToMillis
import java.nio.file.Path
import java.time.Instant

class PreconfiguredSmsNotification(
        private val id: String,
        private val notificationTarget: SmsNotificationTarget,
        private val smsMessageResource: Path,
        private val afterInactivityHours: Float,
        private val lastNotificationInstant: Instant
): Notification {

    override fun notificationTarget(): NotificationTarget = notificationTarget

    override fun inactivityMillisUntilTrigger(): Long = hoursToMillis(afterInactivityHours)
}