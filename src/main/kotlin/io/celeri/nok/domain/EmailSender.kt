package io.celeri.nok.domain

interface EmailSender {

    fun send(senderAddress: String, recipientAddress: String, subject: String, body: String)
}