package io.celeri.nok.domain

import java.nio.file.Path
import java.time.Instant
import java.util.*

class EmailNotificationPlan(
        val id: UUID,
        val notificationRecipient: EmailNotificationRecipient,
        val emailSubject: String,
        val emailMessageResourcePath: Path,
        val heartbeatToTriggerMillis: Long,
        val lastNotification: Instant,
        private val emailSender: EmailSender

): NotificationPlan {

    override fun recipient(): NotificationRecipient = notificationRecipient

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis

    override fun sendNotificationIfNeeded() {
        TODO("Not yet implemented")
    }
}