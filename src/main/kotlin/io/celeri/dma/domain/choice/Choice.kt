package io.celeri.dma.domain.choice

abstract class Choice(
        val label: String,
        val children: ArrayList<StandardChoice> = ArrayList(),
        val possibleProperties: ArrayList<TopChoice> = ArrayList())
