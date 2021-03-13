package io.celeri.nok.domain

class Sms(
        private val phoneNumber: String,
        private val text: String
) {

    fun send(smsSender: SmsSender) {
        smsSender.send(phoneNumber, text)
    }
}