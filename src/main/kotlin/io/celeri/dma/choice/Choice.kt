package io.celeri.dma.choice

abstract class Choice(
        val label: String,
        val children: List<StandardChoice>) {

    abstract fun possibleParameters(): List<TopChoice>
}
