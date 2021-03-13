package io.celeri.nok.service

import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service

@Service
@Profile("test")
class DummySmsService {
}