package io.celeri.nok.dto

data class NotificationDto(
        val target: String,
        val inactivityHours: Int,
        val nextNotificationTimeUtc: String)
