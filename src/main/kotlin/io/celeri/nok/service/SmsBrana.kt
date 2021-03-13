package io.celeri.nok.service

import io.celeri.nok.domain.SmsSender
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("production")
class SmsBrana: SmsSender {
}