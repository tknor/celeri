package io.celeri.nok.dao.entity

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class WatchEntity(
        @Id val id: String,
        val heartbeat: Instant
) {
}