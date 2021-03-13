package io.celeri.dma.common

import java.time.Instant

fun Instant.millisSince(): Long {
    return Instant.now().toEpochMilli() - this.toEpochMilli()
}

fun Instant.millisUntil(): Long {
    return this.toEpochMilli() - Instant.now().toEpochMilli()
}