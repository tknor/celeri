package io.celeri.dma.domain.choice

abstract class Choice(
        val label: String,
        private val children: ArrayList<StandardChoice> = ArrayList(),
        val possibleProperties: ArrayList<TopChoice> = ArrayList()) {

    abstract fun isDisplayed(): Boolean

    open fun getChildren(): ArrayList<StandardChoice> {
        return children
    }

    open fun findChild(labels: List<String>, index: Int): StandardChoice? {

        val found = children.find { it.label == labels[index]} ?: return null

        val maxIndex = labels.size - 1

        return if (index == maxIndex) {
            found
        } else {
            found.findChild(labels, index + 1)
        }
    }
}
