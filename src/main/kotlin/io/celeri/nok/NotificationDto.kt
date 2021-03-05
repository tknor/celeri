package io.celeri.nok

data class NotificationDto(
        val target: String,
        val inactivityHours: Int,
        val nextNotificationTimeUtc: String)
