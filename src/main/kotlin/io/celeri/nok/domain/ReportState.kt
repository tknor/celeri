package io.celeri.nok.domain

import io.celeri.dma.common.age
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.time.Duration
import java.time.Instant

class ReportState(
        val id: String,
        val lastEmail: Instant,
        val lastSms: Instant,
        val emailFrequency: Duration,
        val smsFrequency: Duration
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ReportState::class.java)
    }

    fun sendReportsIfNeeded(
            emailSending: () -> Unit,
            smsSending: () -> Unit) {

        Instant.now()
        try {
            val lastEmailAge = this.lastEmail.age()
            val lastSmsAge = this.lastSms.age()

            if (lastEmailAge.seconds) {
                emailSending()
                this.lastEmailReportInstant = ofNow()
            }

            if (lastSmsAge > this.smsReportFrequencyHours) {
                smsSending()
                this.lastSmsReportInstant = ofNow()
            }

        } catch (error) {
            this.logger.errorSpecific("sendReportsIfNeeded()", error)
        }
    }
}