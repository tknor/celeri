package io.celeri.config

import io.celeri.nok.config.NotificationsConfig
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
@EnableConfigurationProperties(NotificationsConfig::class)
class ConfigsTest @Autowired constructor( // TODO is autowired constructor needed?
       val notificationsConfig: NotificationsConfig
) {

    @Value("\${celeri.gmailCredentialsPath}")
    private val gmailCredentialsPath: String = ""

    @Test
    fun some() {
        // TODO use asserts

        println(gmailCredentialsPath)
        println(notificationsConfig.emailNotifications.size)
        println(notificationsConfig.smsNotifications.size)
    }
}