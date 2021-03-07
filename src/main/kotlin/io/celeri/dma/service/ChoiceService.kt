package io.celeri.dma.service

import io.celeri.dma.domain.choice.Choice
import io.celeri.dma.domain.choice.StandardChoice
import io.celeri.dma.domain.choice.TopChoice
import io.celeri.dma.parse.ChoicesParser
import org.springframework.stereotype.Service

class ChoiceService {

    private var topChoices: List<TopChoice> = emptyList()

    fun reloadChoices() {
        topChoices = ChoicesParser().parse("src/main/resources/choices.txt")
    }

    fun creationChoices(): List<TopChoice> {
        return topChoices.filter { it.creation }
    }

    private fun choiceOf(labels: List<String>): Choice? {

        if (labels.isEmpty()) return null
        val found = topChoices.find { it.label == labels.first() } ?: return null

        return if (labels.size == 1) {
            found
        } else {
            found.findChild(labels, 1)
        }
    }

    fun childrenOf(labels: List<String>): List<StandardChoice> {
        return choiceOf(labels)?.children ?: emptyList()
    }

    fun possiblePropertiesOf(labels: List<String>): List<TopChoice> {
        return choiceOf(labels)?.possibleProperties ?: emptyList()
    }
}
