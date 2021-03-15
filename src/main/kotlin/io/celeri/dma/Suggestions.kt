package io.celeri.dma

import io.celeri.dma.parse.SuggestionParser

class Suggestions(private val suggestionParser: SuggestionParser) {

    private var verbs: List<String> = emptyList()
    private var rewards: List<String> = emptyList()
    private var threats: List<String> = emptyList()

    fun loadSuggestions() {

        suggestionParser.parse { verbs: List<String>, rewards: List<String>, threats: List<String> ->
            run {
                this.verbs = verbs
                this.rewards = rewards
                this.threats = threats
            }
        }
    }

    fun suggestions(count: Int): List<String> {
        val suggestions = ArrayList<String>()

        repeat(count) {
            suggestions.add("${verbs.random()} towards ${rewards.random()}")
            suggestions.add("${verbs.random()} against ${threats.random()}")
        }

        return suggestions
    }
}
