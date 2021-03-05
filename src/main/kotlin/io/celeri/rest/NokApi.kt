package io.celeri.rest

import io.celeri.nok.DefibrillationDto
import io.celeri.nok.NotificationDto
import io.celeri.nok.ReportDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/nok")
class NokApi {

    @GetMapping("/report")
    fun report(): ReportDto {
        return ReportDto("someUtcTime", Collections.singletonList(NotificationDto("someTarget", 666, "someTimeUtc")))
    }

    @PostMapping("/defibrillate")
    fun defibrillate(defibrillation: DefibrillationDto): ReportDto {
        return ReportDto("someUtcTime", Collections.singletonList(NotificationDto("someTarget", 666, "someTimeUtc")))
    }
}
