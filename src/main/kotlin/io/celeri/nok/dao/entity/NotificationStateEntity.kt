package io.celeri.nok.dao.entity

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class NotificationStateEntity(
        @Id val id: String,
        val lastNotification: Instant
) {
}