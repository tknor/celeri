package io.celeri.dma.domain.choice

import io.celeri.dma.common.ReferenceChoiceChildrenList
import kotlin.collections.ArrayList

class ReferenceChoice(
        label: String,
        displayed: Boolean,
        val reference: TopChoice,
        possibleProperties: ArrayList<TopChoice> = ArrayList()) : StandardChoice(label, displayed, ReferenceChoiceChildrenList(), possibleProperties) {

    override fun getChildren(): ArrayList<StandardChoice> {
        return reference.getChildren()
    }

    override fun toString(): String {
        return "ReferenceChoice($label)"
    }

    override fun findChild(labels: List<String>, index: Int): StandardChoice? {

        val found = reference.getChildren().find { it.label == labels[index]} ?: return null

        val maxIndex = labels.size - 1

        return if (index == maxIndex) {
            found
        } else {
            found.findChild(labels, index + 1)
        }
    }
}
