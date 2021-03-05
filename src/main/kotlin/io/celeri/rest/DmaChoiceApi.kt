package io.celeri.rest

import io.celeri.dma.choice.StaticChoices
import io.celeri.dma.dto.ChoiceDto
import io.celeri.dma.dto.ChoicesDto
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/dma/choices")
@CrossOrigin
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
    fun choicesOf(selections: ChoicesDto): ChoicesDto {
        return ChoicesDto(
                StaticChoices(Collections.emptyList())
                        .childrenOf(selections.choices.map { it.label })
                        .map { ChoiceDto(it.label) }
                        .toList())
    }

    @PostMapping("/properties-of")
    fun propertiesOf(selections: ChoicesDto): ChoicesDto {
        return ChoicesDto(
                StaticChoices(Collections.emptyList())
                        .childrenOf(selections.choices.map { it.label })
                        .map { ChoiceDto(it.label) }
                        .toList())
    }
}
