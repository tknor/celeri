package io.celeri.nok.domain

import io.celeri.dma.common.millisSince
import io.celeri.nok.domain.change.ReportStateChangeObserver
import java.time.Instant

class ReportState(
        var lastEmail: Instant,
        var lastSms: Instant,
        val emailFrequencyMillis: Long,
        val smsFrequencyMillis: Long,
        private val reportStateChangeObserver: ReportStateChangeObserver,
        private val emailSender: EmailSender,
        private val smsSender: SmsSender
) {

    fun sendReportsIfNeeded() {

        val lastEmailAge = lastEmail.millisSince()
        val lastSmsAge = lastSms.millisSince()

        if (lastEmailAge > emailFrequencyMillis) {
            createAndSendEmail()
            lastEmail = Instant.now()
            reportStateChangeObserver.reportEmailSent(this)
        }

        if (lastSmsAge > smsFrequencyMillis) {
            createAndSendSms()
            this.lastSms = Instant.now()
            reportStateChangeObserver.reportSmsSent(this)
        }
    }

    private fun createAndSendEmail() {
        TODO()
    }

    private fun createAndSendSms() {
        TODO()
    }
}