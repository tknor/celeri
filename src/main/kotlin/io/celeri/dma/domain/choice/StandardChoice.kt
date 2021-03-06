package io.celeri.dma.domain.choice

import java.util.*

open class StandardChoice(
        label: String,
        private val displayed: Boolean = true,
        children: ArrayList<StandardChoice> = ArrayList(),
        possibleProperties: ArrayList<TopChoice> = ArrayList()) : Choice(label, children, possibleProperties) {

    override fun toString(): String {
        return "StandardChoice($label)"
    }

    override fun isDisplayed(): Boolean {
        return displayed
    }
}
