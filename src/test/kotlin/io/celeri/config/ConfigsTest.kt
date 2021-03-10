package io.celeri.config

import io.celeri.nok.config.NotificationsConfig
import io.celeri.nok.dao.NotificationStateRepo
import io.celeri.nok.dao.ReportStateRepo
import io.celeri.nok.dao.WatchRepo
import io.celeri.nok.dao.entity.WatchEntity
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import java.time.Instant

@SpringBootTest
@EnableConfigurationProperties(NotificationsConfig::class)
class ConfigsTest @Autowired constructor(
       val notificationsConfig: NotificationsConfig
) {

    @Value("\${celeri.gmailCredentialsPath}")
    private val gmailCredentialsPath: String = ""

    @Test
    fun some() {
        println(gmailCredentialsPath)
        println(notificationsConfig.emailNotifications.size)
        println(notificationsConfig.smsNotifications.size)
    }
}