package io.celeri.rest

import io.celeri.dma.common.DomainObjectNotFoundException
import io.celeri.dma.common.hoursToMillis
import io.celeri.dma.common.millisToHours
import io.celeri.nok.domain.Watch
import io.celeri.nok.dto.ButtonDto
import io.celeri.nok.dto.DefibrillationDto
import io.celeri.nok.dto.NotificationDto
import io.celeri.nok.dto.ReportDto
import io.celeri.nok.service.api.DomainProvider
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/nok")
@CrossOrigin
class NokApi(private val domainProvider: DomainProvider) {

    @GetMapping("/report")
    fun report(): ReportDto {

        return generateReport(domainProvider.watch() ?: throw DomainObjectNotFoundException())
    }

    @PostMapping("/defibrillate")
    fun defibrillate(@RequestBody defibrillation: DefibrillationDto): ReportDto {

        val watch = domainProvider.watch() ?: throw DomainObjectNotFoundException()

        watch.defibrillate(hoursToMillis(defibrillation.hourOffset))
        return generateReport(watch)
    }

    private fun generateReport(watch: Watch): ReportDto {
        return ReportDto(
                watch.nextNotificationTime().toEpochMilli().toString(),
                generateButtons(watch),
                generateNotifications(watch))
    }

    private fun generateButtons(watch: Watch): List<ButtonDto> {
        val now = Instant.now()
        val nextNotificationTimeForZeroOffsetClick = now.plusMillis(watch.nextNotificationHeartbeatToTriggerMillis())

        return listOf(-2, -1, 0, 1, 2)
                .map {
                    ButtonDto(
                            it,
                            nextNotificationTimeForZeroOffsetClick
                                    .plusMillis((hoursToMillis(it)))
                                    .toEpochMilli()
                                    .toString())
                }
    }

    private fun generateNotifications(watch: Watch): List<NotificationDto> {
        val now = Instant.now()

        return watch.notificationPlans
                .map { NotificationDto(
                        it.recipient().toString(),
                        millisToHours(it.heartbeatToTriggerMillis()),
                        watch.heartbeat.plusMillis(it.heartbeatToTriggerMillis()).toEpochMilli().toString()) }
    }
}
