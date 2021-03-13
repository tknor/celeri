package io.celeri.nok.domain

import java.nio.file.Path
import java.time.Instant
import java.util.*

class SmsNotificationPlan(
        val id: UUID,
        val smsNotificationRecipient: SmsNotificationRecipient,
        val smsMessageResourcePath: Path,
        val heartbeatToTriggerMillis: Long,
        val lastNotification: Instant,
        private val smsSender: SmsSender

): NotificationPlan {

    override fun recipient(): NotificationRecipient = smsNotificationRecipient

    override fun heartbeatToTriggerMillis(): Long = heartbeatToTriggerMillis

    override fun sendNotificationIfNeeded() {
        TODO("Not yet implemented")
    }
}