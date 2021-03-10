package io.celeri.nok.dao.entity

import java.time.Instant
import java.util.*
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.ManyToOne

@Entity
class EmailNotificationEntity(
        @Id val id: UUID,
        val recipientEmail: String,
        val emailSubject: String,
        val emailMessageResourcePath: String,
        val heartbeatToTriggerMillis: Long,
        val lastNotification: Instant,
        @ManyToOne val watch: WatchEntity
)