package io.celeri.dma.domain.choice

class TopChoice(
        label: String,
        val creation: Boolean,
        children: ArrayList<StandardChoice> = ArrayList(),
        possibleProperties: ArrayList<TopChoice> = ArrayList()) : Choice(label, children, possibleProperties) {

    override fun toString(): String {
        return "TopChoice($label)"
    }
}
