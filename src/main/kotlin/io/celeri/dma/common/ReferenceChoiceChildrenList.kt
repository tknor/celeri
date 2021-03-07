package io.celeri.dma.common

import java.lang.RuntimeException

class ReferenceChoiceChildrenList<E>: ArrayList<E>() {

    private val msg = "cannot add children to reference choice"

    override fun addAll(elements: Collection<E>): Boolean {
        throw RuntimeException(msg)
    }

    override fun addAll(index: Int, elements: Collection<E>): Boolean {
        throw RuntimeException(msg)
    }

    override fun add(element: E): Boolean {
        throw RuntimeException(msg)
    }

    override fun add(index: Int, element: E) {
        throw RuntimeException(msg)
    }
}