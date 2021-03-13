package io.celeri.nok.domain

class SmsNotificationRecipient(private val phoneNumber: String): NotificationRecipient {

    override fun toString(): String = phoneNumber
}