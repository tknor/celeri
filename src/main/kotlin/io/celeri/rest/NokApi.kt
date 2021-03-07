package io.celeri.rest

import io.celeri.nok.dto.ButtonDto
import io.celeri.nok.dto.DefibrillationDto
import io.celeri.nok.dto.NotificationDto
import io.celeri.nok.dto.ReportDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/nok")
@CrossOrigin
class NokApi {

    @GetMapping("/report")
    fun report(): ReportDto {
        return ReportDto(
                "1551546000000",
                listOf(
                    ButtonDto(-1, "1551546000000"),
                    ButtonDto(0, "1551546000000"),
                    ButtonDto(1, "1551546000000")
                ),
                listOf(
                    NotificationDto("someTarget1", 666, "1551546000000"),
                    NotificationDto("someTarget2", 69, "1551546000000")
                )
        )
    }

    @PostMapping("/defibrillate")
    fun defibrillate(@RequestBody defibrillation: DefibrillationDto): ReportDto {

        return ReportDto(
                "1551546000000",
                listOf(
                        ButtonDto(-1, "1551546000000"),
                        ButtonDto(0, "1551546000000"),
                        ButtonDto(1, "1551546000000")
                ),
                listOf(
                        NotificationDto("someTarget1", 666, "1551546000000"),
                        NotificationDto("someTarget2", 69, "1551546000000")
                )
        )
    }
}
