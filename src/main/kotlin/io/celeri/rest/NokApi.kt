package io.celeri.rest

import io.celeri.common.hoursToMillis
import io.celeri.common.millisToHours
import io.celeri.nok.Nok
import io.celeri.nok.domain.Watch
import io.celeri.nok.dto.ButtonDto
import io.celeri.nok.dto.DefibrillationDto
import io.celeri.nok.dto.NotificationDto
import io.celeri.nok.dto.ReportDto
import org.springframework.web.bind.annotation.*
import java.time.Instant

@RestController
@RequestMapping("/nok")
@CrossOrigin
class NokApi(private val nok: Nok) {

    @GetMapping("/report")
    fun report(): ReportDto {
        return generateReport(nok.watch)
    }

    @PostMapping("/defibrillate")
    fun defibrillate(@RequestBody defibrillation: DefibrillationDto): ReportDto {
        nok.watch.defibrillate(hoursToMillis(defibrillation.hourOffset))
        return generateReport(nok.watch)
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

        return watch.notifications
                .map { NotificationDto(
                        it.recipient().toString(),
                        millisToHours(it.heartbeatToTriggerMillis()),
                        watch.heartbeat.plusMillis(it.heartbeatToTriggerMillis()).toEpochMilli().toString()) }
    }
}
