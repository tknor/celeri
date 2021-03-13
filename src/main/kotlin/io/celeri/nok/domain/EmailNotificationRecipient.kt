package io.celeri.nok.domain

class EmailNotificationRecipient(private val emailAddress: String): NotificationRecipient {

    override fun toString(): String = emailAddress
}