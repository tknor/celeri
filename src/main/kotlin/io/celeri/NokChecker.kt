package io.celeri

import io.celeri.dma.common.DomainObjectNotFoundException
import io.celeri.nok.service.DomainObjectService
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

@Component
@Scope("singleton")
class NokChecker(
        val domainObjectService: DomainObjectService
) {

    private val scheduler: ScheduledExecutorService = Executors.newSingleThreadScheduledExecutor()
    private val task = Runnable { check() }

    fun start(rateSeconds: Int) {
        if (!scheduler.isTerminated) {
            scheduler.scheduleAtFixedRate(task, 5, rateSeconds.toLong(), TimeUnit.SECONDS)
        }
    }

    fun stop() {
        scheduler.shutdown()
    }

    private fun check() {
        val watch = domainObjectService.watch() ?: throw DomainObjectNotFoundException()

    }
}