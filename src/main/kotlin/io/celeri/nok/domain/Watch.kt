package io.celeri.nok.domain

import io.celeri.nok.domain.change.WatchChangeObserver
import io.celeri.nok.service.api.SenderProvider
import java.time.Instant

class Watch(
        val id: String,
        var heartbeat: Instant,
        val notificationPlans: List<NotificationPlan>,
        private val watchChangeObserver: WatchChangeObserver
) {

    fun nextNotificationTime(): Instant {
        if (notificationPlans.isEmpty()) return Instant.MAX

        val nextNotification = notificationPlans.sortedBy { it.heartbeatToTriggerMillis() }.first()
        return heartbeat.plusMillis(nextNotification.heartbeatToTriggerMillis())
    }

    fun nextNotificationHeartbeatToTriggerMillis(): Long {
        if (notificationPlans.isEmpty()) return Long.MAX_VALUE

        return notificationPlans.sortedBy { it.heartbeatToTriggerMillis() }.first().heartbeatToTriggerMillis()
    }

    fun defibrillate(millisOffset: Long) {
        this.heartbeat = Instant.now().plusMillis(millisOffset)
        this.watchChangeObserver.watchHeartbeatChanged(this)
    }

    fun sendNotificationsIfNeeded() {
        notificationPlans.forEach { it.sendNotificationIfNeeded() }
    }
}