package io.celeri.rest

import io.celeri.dma.dto.ChoiceDto
import io.celeri.dma.dto.ChoicesDto
import io.celeri.dma.service.ChoiceService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dma/choices")
@CrossOrigin
class DmaChoiceApi(private val choiceService: ChoiceService) {

    @GetMapping("/reload")
    fun reloadChoices() {
        choiceService.reloadChoices()
    }

    @GetMapping("/creation-choices")
    fun creationChoices(): ChoicesDto {
        return ChoicesDto(choiceService.creationChoices().map { ChoiceDto(it.label, it.isDisplayed()) })
    }

    @PostMapping("/children-of")
    fun childrenOf(@RequestBody selections: ChoicesDto): ChoicesDto {

        // TODO error handling, null checks on dto, or something

        val labels = selections.choices.map { it.label }
        return ChoicesDto(choiceService.childrenOf(labels).map { ChoiceDto(it.label, it.isDisplayed()) })
    }

    @PostMapping("/properties-of")
    fun propertiesOf(@RequestBody selections: ChoicesDto): ChoicesDto {

        val labels = selections.choices.map { it.label }
        return ChoicesDto(choiceService.possiblePropertiesOf(labels).map { ChoiceDto(it.label, it.isDisplayed()) })
    }
}
