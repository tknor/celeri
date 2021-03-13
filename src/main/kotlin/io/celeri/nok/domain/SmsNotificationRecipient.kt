package io.celeri.nok.domain

class SmsNotificationRecipient(val phoneNumber: String): NotificationRecipient {

    override fun toString(): String = phoneNumber
}