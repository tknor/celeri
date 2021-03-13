package io.celeri.nok.domain

interface NotificationPlan {

    fun recipient(): NotificationRecipient
    fun heartbeatToTriggerMillis(): Long
    fun sendNotificationIfNeeded()
}