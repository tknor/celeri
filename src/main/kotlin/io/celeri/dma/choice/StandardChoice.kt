package io.celeri.dma.choice

import java.util.*

open class StandardChoice(
        label: String,
        children: List<StandardChoice>,
        val displayed: Boolean = true,
        private val internalPossibleParameters: List<TopChoice> = Collections.emptyList()) : Choice(label, children) {

    override fun possibleParameters(): List<TopChoice> {
        return internalPossibleParameters
    }
}
