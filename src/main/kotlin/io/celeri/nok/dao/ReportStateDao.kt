package io.celeri.nok.dao

import java.time.Instant

data class ReportStateDao(
        val lastEmailReportInstant: Instant,
        val lastSmsReportInstant: Instant,
        val emailReportFrequencyHours: Int,
        val smsReportFrequencyHours: Int
)