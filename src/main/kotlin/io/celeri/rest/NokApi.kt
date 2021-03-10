package io.celeri.rest

import io.celeri.dma.common.hoursToMillis
import io.celeri.dma.common.millisToHours
import io.celeri.nok.domain.Watch
import io.celeri.nok.dto.ButtonDto
import io.celeri.nok.dto.DefibrillationDto
import io.celeri.nok.dto.NotificationDto
import io.celeri.nok.dto.ReportDto
import io.celeri.nok.service.DomainObjectService
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/nok")
@CrossOrigin
class NokApi(private val domainObjectService: DomainObjectService) {

    val noWatchFound = RuntimeException("no watch found")

    @GetMapping("/report")
    fun report(): ReportDto {

        return generateReport(domainObjectService.watch() ?: throw noWatchFound)
    }

    @PostMapping("/defibrillate")
    fun defibrillate(@RequestBody defibrillation: DefibrillationDto): ReportDto {

        val watch = domainObjectService.watch() ?: throw noWatchFound

        watch.defibrillate(hoursToMillis(defibrillation.hourOffset))
        return generateReport(watch)
    }

    private fun generateReport(watch: Watch): ReportDto {
        return ReportDto(
                watch.nextNotificationTime().toEpochMilli().toString(),
                generateButtons(watch),
                generateNotifications(watch))
    }

    private fun generateButtons(watch: Watch): List<ButtonDto> = listOf(-1, 0, 1)
            .map { ButtonDto(
                    it,
                    watch.nextNotificationTime(hoursToMillis(it))
                            .toEpochMilli()
                            .toString()) }

    private fun generateNotifications(watch: Watch): List<NotificationDto> {
        val now = Instant.now()

        return watch.notifications
                .map { NotificationDto(
                        it.notificationTarget().stringRepresentation(),
                        millisToHours(it.heartbeatToTriggerMillis()),
                        now.plusMillis(it.heartbeatToTriggerMillis()).toEpochMilli().toString()) }
    }
}
