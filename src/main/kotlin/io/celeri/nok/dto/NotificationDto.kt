package io.celeri.nok.dto

data class NotificationDto(
        val target: String,
        val inactivityHours: Int, // TODO change to inactivity minutes
        val nextNotificationTimeUtc: String)
