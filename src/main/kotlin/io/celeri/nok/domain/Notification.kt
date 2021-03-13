package io.celeri.nok.domain

interface Notification {

    fun recipient(): NotificationRecipient
    fun heartbeatToTriggerMillis(): Long
    fun sendIfNeeded()
}