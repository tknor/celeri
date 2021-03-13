package io.celeri.rest

import io.celeri.dma.dto.ChoiceDto
import io.celeri.dma.dto.ChoicesDto
import io.celeri.dma.Choices
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dma/choices")
@CrossOrigin
class DmaChoiceApi(private val choices: Choices) {

    @GetMapping("/reload")
    fun reloadChoices() {
        choices.loadChoices()
    }

    @GetMapping("/creation-choices")
    fun creationChoices(): ChoicesDto {
        return ChoicesDto(choices.creationChoices().map { ChoiceDto(it.label, it.isDisplayed()) })
    }

    @PostMapping("/children-of")
    fun childrenOf(@RequestBody selections: ChoicesDto): ChoicesDto {

        // TODO error handling, null checks on dto, or something

        val labels = selections.choices.map { it.label }
        return ChoicesDto(choices.childrenOf(labels).map { ChoiceDto(it.label, it.isDisplayed()) })
    }

    @PostMapping("/properties-of")
    fun propertiesOf(@RequestBody selections: ChoicesDto): ChoicesDto {

        val labels = selections.choices.map { it.label }
        return ChoicesDto(choices.possiblePropertiesOf(labels).map { ChoiceDto(it.label, it.isDisplayed()) })
    }
}
