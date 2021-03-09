package io.celeri.nok.domain

class SmsNotificationTarget(
        private val phoneNumber: String
): NotificationTarget {

    override fun stringRepresentation(): String = phoneNumber
}