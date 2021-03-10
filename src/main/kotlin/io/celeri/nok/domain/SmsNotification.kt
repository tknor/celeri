package io.celeri.nok.domain

import java.nio.file.Path
import java.time.Instant
import java.util.*

class SmsNotification(
        val id: UUID,
        val notificationTarget: SmsNotificationTarget,
        val smsMessageResourcePath: Path,
        val heartbeatToTriggerMillis: Long,
        val lastNotification: Instant
): Notification {

    override fun notificationTarget(): NotificationTarget = notificationTarget

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis()
}