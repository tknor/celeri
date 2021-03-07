package io.celeri.dma.domain.choice

import io.celeri.dma.common.ReferenceChoiceChildrenList
import java.util.ArrayList

class ReferenceChoice(
        label: String,
        displayed: Boolean,
        val reference: TopChoice,
        possibleProperties: ArrayList<TopChoice> = ArrayList()) : StandardChoice(label, displayed, ReferenceChoiceChildrenList(), possibleProperties) {

    override fun toString(): String {
        return "ReferenceChoice($label)"
    }
}
