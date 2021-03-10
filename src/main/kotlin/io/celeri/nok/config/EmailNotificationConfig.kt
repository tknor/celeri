package io.celeri.nok.config

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class EmailNotificationConfig(
        val id: String,
        val recipientEmail: String,
        val emailSubject: String,
        val emailMessageResource: String, // TODO try Path
        val afterInactivityHours: Float
)