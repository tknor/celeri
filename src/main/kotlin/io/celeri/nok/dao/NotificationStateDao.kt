package io.celeri.nok.dao

import java.time.Instant

data class NotificationStateDao(
        val notificationId: String,
        val notificationInstant: Instant
)