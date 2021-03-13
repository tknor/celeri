package io.celeri.nok.domain

interface SmsSender {

    fun send(phoneNumber: String, text: String)
    fun remainingCredit(creditCzk: Int)
}