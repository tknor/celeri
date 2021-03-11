package io.celeri.nok.config

import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
class SmsNotificationConfig(
        val recipientPhoneNumber: String,
        val smsMessageResourcePath: String,
        val heartbeatToTriggerMinutes: Int
)