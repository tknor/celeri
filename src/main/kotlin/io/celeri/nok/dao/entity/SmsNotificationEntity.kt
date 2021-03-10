package io.celeri.nok.dao.entity

import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class SmsNotificationEntity(
        @Id val id: UUID,
        val recipientPhoneNumber: String,
        val smsMessageResourcePath: String,
        val heartbeatToTriggerMillis: Long,
        val lastNotification: Instant,
        @ManyToOne val watch: WatchEntity
)