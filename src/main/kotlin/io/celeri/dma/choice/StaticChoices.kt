package io.celeri.dma.choice

import java.util.*

class StaticChoices(val topChoices: List<TopChoice>) {

    fun creationChoices(): List<TopChoice> {
        return topChoices.filter { it.creation }
    }

    fun choiceOf(labels: List<String>): Choice {
        return StandardChoice("umbekante", Collections.emptyList())
    }

    fun childrenOf(labels: List<String>): List<StandardChoice> {
        return choiceOf(labels).children
    }

    fun possibleParametersOf(labels: List<String>): List<TopChoice> {
        return choiceOf(labels).possibleParameters()
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
