package io.celeri.dma.service

import io.celeri.dma.parse.ChoicesParser
import org.springframework.context.annotation.Configuration


class ChoiceServiceConfig {

    fun choiceService(): ChoiceService {
        return ChoiceService(ChoicesParser().parse("choices.txt"))
    }
}