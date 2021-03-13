package io.celeri.nok.domain

import java.nio.file.Path
import java.time.Instant

class EmailNotification(
        private val recipient: EmailNotificationRecipient,
        private val emailSubject: String,
        private val emailMessageResourcePath: Path,
        private val heartbeatToTriggerMillis: Long,
        private val lastNotification: Instant,
        private val emailSender: EmailSender

): Notification {

    override fun recipient(): NotificationRecipient = recipient

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis

    override fun sendIfNeeded() {
        // TODO implement
    }
}