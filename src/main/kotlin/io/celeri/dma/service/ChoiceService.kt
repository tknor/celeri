package io.celeri.dma.service

import io.celeri.dma.domain.choice.Choice
import io.celeri.dma.domain.choice.StandardChoice
import io.celeri.dma.domain.choice.TopChoice
import org.springframework.stereotype.Service
import java.util.*


class ChoiceService(val topChoices: List<TopChoice>) {

    fun creationChoices(): List<TopChoice> {
        return topChoices.filter { it.creation }
    }

    fun choiceOf(labels: List<String>): Choice {
        return StandardChoice("umbekante")
    }

    fun childrenOf(labels: List<String>): List<StandardChoice> {
        return choiceOf(labels).children
    }

    fun possiblePropertiesOf(labels: List<String>): List<TopChoice> {
        return choiceOf(labels).possibleProperties
    }
}

//export class ChoiceDatabase {
//
//   constructor() {
//   }
//
//   choices(allowedTopLevelChoices: string[]): ChoiceDto[] {
//
//     return CHOICES.filter(choice => {
//       for (const allowed of allowedTopLevelChoices) {
//         if (choice.label === allowed) {
//           return true
//         }
//       }
//       return false
//     })
//   }
//
//   allowedTopLevelChoicesForRootSelection(): string[] {
//     return ALLOWED_TOP_FOR_ROOT
//   }
//
//   findProperties(labels: string[]): string[] {
//
//     const found = this.recursiveSearch(labels, 0, CHOICES)
//
//     if (found) {
//       found.possibleProperties.push("Feature")
//       return found.possibleProperties
//     } else {
//       return ALLOWED_TOP_FOR_ROOT // TODO separate field for this in future?
//     }
//   }
//
//   private recursiveSearch(labels: string[], labelIndex: number, choices: ChoiceDto[]): ChoiceDto {
//
//     let found: ChoiceDto = choices.find(choice => choice.label === labels[labelIndex])
//
//     if (found) {
//       let maxIndex = labels.length - 1
//
//       if (labelIndex == maxIndex) {
//         return found
//
//       } else {
//
//         if (found.children.length > 0) {
//           return this.recursiveSearch(labels, ++labelIndex, found.children)
//         } else {
//           return null
//         }
//       }
//
//     } else {
//       return null
//     }
//   }
// }
