package io.celeri.nok.service

import io.celeri.nok.domain.EmailSender
import org.springframework.stereotype.Service

@Service
class DummyEmailSender: EmailSender {

    override fun send(senderAddress: String, recipientAddress: String, subject: String, body: String) {
        // TODO implement
    }
}