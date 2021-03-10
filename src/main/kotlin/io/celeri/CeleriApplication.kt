package io.celeri

import io.celeri.nok.config.NotificationsConfig
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.context.event.EventListener


@SpringBootApplication
@EnableConfigurationProperties(NotificationsConfig::class)
class CeleriApplication {

	@EventListener(ApplicationReadyEvent::class)
	fun afterStartup() {
		println("here goes initialization code")
	}
}

fun main(args: Array<String>) {
	runApplication<CeleriApplication>(*args)
}
