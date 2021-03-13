package io.celeri.nok.domain

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class NokCheck(
        private val reportState: ReportState,
        private val watch: Watch
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(NokCheck::class.java)
    }

    private fun check() {
        log.info("check start")

        reportState.sendReportsIfNeeded()
        watch.sendNotificationsIfNeeded()

        log.info("check end")
    }

    fun toRunnable(): Runnable = Runnable { check() }
}