package io.celeri.nok.domain

import io.celeri.nok.hoursToMillis
import java.nio.file.Path
import java.time.Instant
import java.util.*

class SmsNotification(
        private val id: UUID,
        private val notificationTarget: SmsNotificationTarget,
        private val smsMessageResourcePath: Path,
        private val heartbeatToTriggerMillis: Float,
        private val lastNotification: Instant
): Notification {

    override fun notificationTarget(): NotificationTarget = notificationTarget

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis()
}