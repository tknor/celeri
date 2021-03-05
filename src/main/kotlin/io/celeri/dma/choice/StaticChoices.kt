package io.celeri.dma.choice

import java.util.*

class StaticChoices(val topChoices: List<TopChoice>) {

    fun creationChoices(): List<TopChoice> {
        return topChoices.filter { it.creation }
    }

    fun choiceOf(labels: List<String>): Choice {
        return StandardChoice("umbekante", Collections.emptyList())
    }

    fun childrenOf(labels: List<String>): List<StandardChoice> {
        return choiceOf(labels).children
    }

    fun possibleParametersOf(labels: List<String>): List<TopChoice> {
        return choiceOf(labels).possibleParameters()
    }
}
