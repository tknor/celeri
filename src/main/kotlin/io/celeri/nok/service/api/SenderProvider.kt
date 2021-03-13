package io.celeri.nok.service.api

import io.celeri.nok.domain.EmailSender
import io.celeri.nok.domain.SmsSender

interface SenderProvider {

    fun emailSender(): EmailSender
    fun smsSender(): SmsSender
}
