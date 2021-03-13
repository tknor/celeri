package io.celeri.nok.config

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class EmailNotificationConfig(
        val recipientAddress: String,
        val emailSubject: String,
        val emailMessageResourcePath: String,
        val heartbeatToTriggerMinutes: Int
)