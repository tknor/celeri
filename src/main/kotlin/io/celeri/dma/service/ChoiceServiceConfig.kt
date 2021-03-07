package io.celeri.dma.service

import io.celeri.dma.parse.ChoicesParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChoiceServiceConfig {

    @Bean
    fun choiceService(): ChoiceService {
        return ChoiceService(ChoicesParser().parse("src/main/resources/choices.txt"))
    }
}