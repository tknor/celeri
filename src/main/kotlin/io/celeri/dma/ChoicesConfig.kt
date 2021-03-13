package io.celeri.dma

import io.celeri.dma.parse.ChoicesParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class ChoicesConfig {

    @Bean
    @Scope("singleton")
    fun choices(): Choices {

        val choices = Choices(ChoicesParser("src/main/resources/choices.txt"))
        choices.loadChoices()
        return choices
    }
}