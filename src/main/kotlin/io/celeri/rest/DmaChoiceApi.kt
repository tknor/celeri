package io.celeri.rest

import io.celeri.dma.dto.ChoiceDto
import io.celeri.dma.dto.ChoicesDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dma/choices")
@CrossOrigin
class DmaChoiceApi {

    @GetMapping("/creation-choices")
    fun creations(): ChoicesDto {
//        return ChoicesDto(
//                StaticChoices(Collections.emptyList())
//                        .creationChoices()
//                        .map { ChoiceDto(it.label) }
//                        .toList())
        println("CALLED " + "creations")
        return ChoicesDto(listOf(
                ChoiceDto("creation1"),
                ChoiceDto("creation2"),
                ChoiceDto("creation3")
        ))
    }

    @PostMapping("/children-of")
    fun childrenOf(@RequestBody selections: ChoicesDto): ChoicesDto {
        // TODO error handling, null checks on dto, or something
//        return ChoicesDto(
//                StaticChoices(Collections.emptyList())
//                        .childrenOf(selections.choices.map { it.label })
//                        .map { ChoiceDto(it.label) }
//                        .toList())
        println("CALLED " + "childrenOf")
        return ChoicesDto(listOf(
                ChoiceDto("child1"),
                ChoiceDto("child2"),
                ChoiceDto("child3")
        ))
    }

    @PostMapping("/properties-of")
    fun propertiesOf(@RequestBody selections: ChoicesDto): ChoicesDto {
//        return ChoicesDto(
//                StaticChoices(Collections.emptyList())
//                        .childrenOf(selections.choices.map { it.label })
//                        .map { ChoiceDto(it.label) }
//                        .toList())
        println("CALLED " + "propertiesOf")
        return ChoicesDto(listOf(
                ChoiceDto("property1"),
                ChoiceDto("property2"),
                ChoiceDto("property3")
        ))
    }
}
