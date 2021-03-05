package io.celeri

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CeleriApplication

fun main(args: Array<String>) {
	runApplication<CeleriApplication>(*args)
}
