package io.celeri.nok.domain

import io.celeri.nok.hoursToMillis
import java.nio.file.Path
import java.time.Instant
import java.util.*

class EmailNotification(
        private val id: UUID,
        private val notificationTarget: EmailNotificationTarget,
        private val emailSubject: String,
        private val emailMessageResourcePath: Path,
        private val heartbeatToTriggerMillis: Long,
        private val lastNotification: Instant
): Notification {

    override fun notificationTarget(): NotificationTarget = notificationTarget

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis
}