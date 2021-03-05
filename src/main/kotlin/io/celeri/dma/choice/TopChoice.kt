package io.celeri.dma.choice

import java.util.*

class TopChoice(
        label: String,
        children: List<StandardChoice>,
        val creation: Boolean) : Choice(label, children) {

    override fun possibleParameters(): List<TopChoice> {
        return Collections.emptyList()
    }
}
