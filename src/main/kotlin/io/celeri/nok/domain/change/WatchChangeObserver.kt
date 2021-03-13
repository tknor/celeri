package io.celeri.nok.domain.change

import io.celeri.nok.domain.EmailNotificationPlan
import io.celeri.nok.domain.SmsNotificationPlan
import io.celeri.nok.domain.Watch

interface WatchChangeObserver {

    fun watchHeartbeatChanged(watch: Watch)
    fun watchEmailNotificationSent(watch: Watch, notification: EmailNotificationPlan)
    fun watchSmsNotificationSent(watch: Watch, notification: SmsNotificationPlan)
}