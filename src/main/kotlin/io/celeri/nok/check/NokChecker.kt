package io.celeri.nok.check

import io.celeri.nok.domain.ReportState
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class NokChecker(
        private val rateMillis: Long,
        private val check: NokCheck
) {

    companion object {
        private val log: Logger = LoggerFactory.getLogger(ReportState::class.java)
    }

    private val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()

    fun start() {
        if (!scheduler.isTerminated) {
            scheduler.scheduleAtFixedRate(check.toRunnable(), 10000, rateMillis, TimeUnit.MILLISECONDS)
            log.info("checker started")
        }
    }

    fun stop() {
        scheduler.shutdown()
        log.info("checker stopping ...")
    }
}