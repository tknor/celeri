package io.celeri.nok.domain

import java.time.Instant

class ReportState(
        val id: String,
        val lastEmail: Instant,
        val lastSms: Instant,
        val emailFrequencyMillis: Long,
        val smsFrequencyMillis: Long
) {
}