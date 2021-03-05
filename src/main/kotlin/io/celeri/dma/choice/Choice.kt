package io.celeri.dma.choice

open abstract class Choice(
        val label: String,
        val children: List<StandardChoice>) {

    abstract fun possibleParameters(): List<TopChoice>
}
