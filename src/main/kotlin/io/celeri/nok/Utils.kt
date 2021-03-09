package io.celeri.nok

fun millisToHours(millis: Long): Int = (millis / 1000 / 60 / 60).toInt()

fun hoursToMillis(hours: Int): Long = (hours * 60 * 60 * 1000).toLong()

fun hoursToMillis(hours: Float): Long = (hours * 60 * 60 * 1000).toLong()
