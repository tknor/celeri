package io.celeri.nok.domain

class EmailNotificationTarget(
    private val emailAddress: String
): NotificationTarget {

    override fun stringRepresentation(): String = emailAddress
}