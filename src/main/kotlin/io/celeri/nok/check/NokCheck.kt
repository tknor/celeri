package io.celeri.nok.check

import io.celeri.nok.Nok
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class NokCheck(private val nok: Nok) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(NokCheck::class.java)
    }

    private fun check() {
        log.info("check start")

        nok.reportState.sendReportsIfNeeded()
        nok.watch.sendNotificationsIfNeeded()

        log.info("check end")
    }

    fun toRunnable(): Runnable = Runnable { check() }
}