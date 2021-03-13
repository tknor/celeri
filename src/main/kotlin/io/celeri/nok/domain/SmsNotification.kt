package io.celeri.nok.domain

import java.nio.file.Path
import java.time.Instant

class SmsNotification(
        private val recipient: SmsNotificationRecipient,
        private val smsMessageResourcePath: Path,
        private val heartbeatToTriggerMillis: Long,
        private val lastNotification: Instant,
        private val smsSender: SmsSender

): Notification {

    override fun recipient(): NotificationRecipient = recipient

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis

    override fun sendIfNeeded() {
        // TODO implement
    }
}