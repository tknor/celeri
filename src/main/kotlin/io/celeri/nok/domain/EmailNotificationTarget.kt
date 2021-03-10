package io.celeri.nok.domain

class EmailNotificationTarget(
    val emailAddress: String
): NotificationTarget {

    override fun stringRepresentation(): String = emailAddress
}