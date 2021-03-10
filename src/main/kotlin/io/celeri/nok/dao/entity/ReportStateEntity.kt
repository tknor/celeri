package io.celeri.nok.dao.entity

import java.time.Instant
import javax.persistence.Entity
import javax.persistence.Id

@Entity
class ReportStateEntity(
        @Id val id: String,
        val lastEmail: Instant,
        val lastSms: Instant,
        val emailFrequency: Long,
        val smsFrequency: Long
) {
}