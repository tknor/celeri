package io.celeri.nok.domain

import io.celeri.common.millisSince
import java.time.Instant

class ReportState(
        private var lastEmail: Instant,
        private var lastSms: Instant,
        private val emailFrequencyMillis: Long,
        private val smsFrequencyMillis: Long,
        private val emailSender: EmailSender,
        private val smsSender: SmsSender
) {

    fun sendReportsIfNeeded() {

        val lastEmailAge = lastEmail.millisSince()
        val lastSmsAge = lastSms.millisSince()

        if (lastEmailAge > emailFrequencyMillis) {
            createAndSendEmail()
            lastEmail = Instant.now()
        }

        if (lastSmsAge > smsFrequencyMillis) {
            createAndSendSms()
            this.lastSms = Instant.now()
        }
    }

    private fun createAndSendEmail() {
        // TODO implement
    }

    private fun createAndSendSms() {
        // TODO implement
    }
}