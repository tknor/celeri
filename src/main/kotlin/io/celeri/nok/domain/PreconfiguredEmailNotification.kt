package io.celeri.nok.domain

import io.celeri.nok.hoursToMillis
import java.nio.file.Path
import java.time.Instant

class PreconfiguredEmailNotification(
        private val id: String,
        private val notificationTarget: EmailNotificationTarget,
        private val emailSubject: String,
        private val emailMessageResource: Path,
        private val afterInactivityHours: Float,
        private val lastNotificationInstant: Instant
): Notification {

    override fun notificationTarget(): NotificationTarget = notificationTarget

    override fun inactivityMillisUntilTrigger(): Long = hoursToMillis(afterInactivityHours)
}