package io.celeri.nok.domain

import io.celeri.nok.domain.change.WatchChangeObserver
import java.time.Instant

class Watch(
        val id: String,
        var heartbeat: Instant,
        val notifications: List<Notification>,
        private val watchChangeObserver: WatchChangeObserver
) {

    fun nextNotificationTime(): Instant {

        if (notifications.isEmpty()) return Instant.MAX

        val nextNotification = notifications.sortedBy { it.heartbeatToTriggerMillis() }.first()
        return heartbeat.plusMillis(nextNotification.heartbeatToTriggerMillis())
    }

    fun nextNotificationHeartbeatToTriggerMillis(): Long {

        if (notifications.isEmpty()) return Long.MAX_VALUE

        return notifications.sortedBy { it.heartbeatToTriggerMillis() }.first().heartbeatToTriggerMillis()
    }

    fun defibrillate(millisOffset: Long) {
        this.heartbeat = Instant.now().plusMillis(millisOffset)
        this.watchChangeObserver.watchHeartbeatChanged(this)
    }
}