package io.celeri.nok.domain

class EmailNotificationRecipient(val emailAddress: String): NotificationRecipient {

    override fun toString(): String = emailAddress
}