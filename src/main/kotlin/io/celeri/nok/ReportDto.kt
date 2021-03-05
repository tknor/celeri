package io.celeri.nok

data class ReportDto(
        val nextNotificationTimeUtc: String,
        val notifications: List<NotificationDto>)
