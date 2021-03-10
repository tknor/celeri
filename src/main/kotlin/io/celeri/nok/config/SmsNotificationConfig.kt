package io.celeri.nok.config

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class SmsNotificationConfig(
        val id: String,
        val recipientPhoneNumber: String,
        val smsMessageResourcePath: String, // TODO try Path
        val heartbeatToTriggerMinutes: Float
)