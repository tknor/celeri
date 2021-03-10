package io.celeri.dma.common

import java.util.*

fun <T: Any> Optional<T>.asNullable(): T? = this.orElse(null)

fun millisToHours(millis: Long): Int = (millis / 1000 / 60 / 60).toInt()

fun hoursToMillis(hours: Int): Long = (hours * 60 * 60 * 1000).toLong()
