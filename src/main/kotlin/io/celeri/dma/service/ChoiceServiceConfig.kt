package io.celeri.dma.service

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ChoiceServiceConfig {

    @Bean
    fun choiceService(): ChoiceService {

        val choiceService = ChoiceService()
        choiceService.reloadChoices()
        return choiceService
    }
}