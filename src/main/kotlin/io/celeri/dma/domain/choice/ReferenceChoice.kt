package io.celeri.dma.domain.choice

import java.util.ArrayList

class ReferenceChoice(
        label: String,
        displayed: Boolean,
        val reference: TopChoice,
        children: ArrayList<StandardChoice> = ArrayList(),
        possibleProperties: ArrayList<TopChoice> = ArrayList()) : StandardChoice(label, displayed, children, possibleProperties) {

    override fun toString(): String {
        return "ReferenceChoice($label)"
    }
}
