package io.celeri.nok.domain

class SmsNotificationTarget(
        val phoneNumber: String
): NotificationTarget {

    override fun stringRepresentation(): String = phoneNumber
}