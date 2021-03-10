package io.celeri

import io.celeri.nok.config.NotificationsConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(NotificationsConfig::class)
class CeleriApplication

fun main(args: Array<String>) {
	runApplication<CeleriApplication>(*args)
}
