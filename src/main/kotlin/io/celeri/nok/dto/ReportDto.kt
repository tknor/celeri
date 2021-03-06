package io.celeri.nok.dto

data class ReportDto(
        val nextNotificationTimeUtc: String,
        val buttons: List<ButtonDto>,
        val notifications: List<NotificationDto>)
