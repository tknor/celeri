package io.celeri

import io.celeri.nok.Nok
import io.celeri.nok.check.NokCheck
import io.celeri.nok.check.NokChecker
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.Duration

@Component
class AfterStartup(private val nok: Nok) {

    @EventListener(ApplicationReadyEvent::class)
    fun afterStartup() {

        NokChecker(
                Duration.ofSeconds(5).toMillis(),
                NokCheck(nok))
                .start()
    }
}