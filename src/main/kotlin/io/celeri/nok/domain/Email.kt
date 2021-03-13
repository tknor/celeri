package io.celeri.nok.domain

class Email(
        private val senderAddress: String,
        private val recipientAddress: String,
        private val subject: String,
        private val body: String
) {

    fun send(emailSender: EmailSender) {
        emailSender.send(senderAddress, recipientAddress, subject, body)
    }
}