package io.celeri.nok.domain

interface Notification {

    fun notificationTarget(): NotificationTarget
    fun heartbeatToTriggerMillis(): Long
}