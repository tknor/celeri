package io.celeri.rest

import io.celeri.dma.choice.StaticChoices
import io.celeri.dma.dto.ChoiceDto
import io.celeri.dma.dto.ChoiceSpecsDto
import io.celeri.dma.dto.ChoicesDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/dma/choices")
class DmaChoiceApi {

    @GetMapping("/creation-choices")
    fun choices(): ChoicesDto {
        return ChoicesDto(
                StaticChoices(Collections.emptyList())
                        .creationChoices()
                        .map { ChoiceDto(it.label) }
                        .toList())
    }

    @PostMapping("/children-of")
    fun choicesOf(choiceSpecs: ChoiceSpecsDto): ChoicesDto {
        return ChoicesDto(
                StaticChoices(Collections.emptyList())
                        .childrenOf(choiceSpecs.labels)
                        .map { ChoiceDto(it.label) }
                        .toList())
    }
}
