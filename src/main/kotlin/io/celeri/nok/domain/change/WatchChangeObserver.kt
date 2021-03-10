package io.celeri.nok.domain.change

import io.celeri.nok.domain.EmailNotification
import io.celeri.nok.domain.SmsNotification
import io.celeri.nok.domain.Watch

interface WatchChangeObserver {

    fun watchHeartbeatChanged(watch: Watch)
    fun watchEmailNotificationSent(watch: Watch, notification: EmailNotification)
    fun watchSmsNotificationSent(watch: Watch, notification: SmsNotification)
}