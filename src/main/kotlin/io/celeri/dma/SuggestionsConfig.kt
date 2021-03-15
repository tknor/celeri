package io.celeri.dma

import io.celeri.dma.parse.SuggestionParser
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

@Configuration
class SuggestionsConfig {

    @Bean
    @Scope("singleton")
    fun suggestions(): Suggestions {

        val suggestions = Suggestions(SuggestionParser(
                "src/main/resources/verbs.txt",
                "src/main/resources/rewards.txt",
                "src/main/resources/threats.txt"))
        suggestions.loadSuggestions()
        return suggestions
    }
}