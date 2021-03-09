package io.celeri.nok.domain

import java.time.Instant

class Watch(
        val heartbeat: Instant,
        val notifications: List<Notification>
) {

    fun nextNotificationTime(): Instant {
        return Instant.now() // TODO
    }

    fun calculateNotificationTime(millisOffset: Long): Instant {
        return Instant.now() // TODO
    }

    fun defibrillate(millisOffset: Long) {

    }
}