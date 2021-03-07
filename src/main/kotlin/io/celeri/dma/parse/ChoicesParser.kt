package io.celeri.dma.parse

import com.fasterxml.jackson.databind.ObjectMapper
import io.celeri.dma.domain.choice.Choice
import io.celeri.dma.domain.choice.ReferenceChoice
import io.celeri.dma.domain.choice.StandardChoice
import io.celeri.dma.domain.choice.TopChoice
import java.io.FileInputStream
import java.nio.file.Paths

class ChoicesParser {

    private val topChoices: ArrayList<TopChoice> = ArrayList()
    private var previousChoices: ArrayList<Choice> = ArrayList()

    fun parse(filePath: String): List<TopChoice> {

        val parsedLines: ArrayList<ParsedLine> = ArrayList()

        val lines: List<String> = FileInputStream(filePath).bufferedReader().use { it.readLines() }

        for (line in lines) {
            if (line.isBlank()) continue
            parsedLines.add(ParsedLine.parsedLine(line))
        }

        // first run

        for (parsedLine in parsedLines) {
            if (parsedLine.level == 0) {
                topChoices.add(TopChoice(parsedLine.baseLabel, parsedLine.creation))
            }
        }

        // full run

        for (parsedLine in parsedLines) {

            if (parsedLine.level == 0) {

                handleTopChoiceParsedLineType(parsedLine)

            } else if (parsedLine.level > previousChoices.size - 1) { // level is higher than last line

                handleStandardChoiceParsedLineType(parsedLine)

            } else { // level is lower or same as last line

                val levelDiff = (previousChoices.size - 1) - parsedLine.level

                repeat(levelDiff + 1) {
                    previousChoices.removeLast()
                }

                handleStandardChoiceParsedLineType(parsedLine)
            }
        }

        return topChoices
    }

    private fun handleTopChoiceParsedLineType(parsedLine: ParsedLine) {

        previousChoices = ArrayList()
        val topChoice = findTopChoice(parsedLine.baseLabel)
        val possibleProperties = parsedLine.propertyLabels.map { findTopChoice(it) }
        topChoice.possibleProperties.addAll(possibleProperties)
        previousChoices.add(topChoice)
    }

    private fun handleStandardChoiceParsedLineType(parsedLine: ParsedLine) {

        val possibleProperties = parsedLine.propertyLabels.map { findTopChoice(it) }.toCollection(ArrayList())
        if (parsedLine.reference) {
            val reference = findTopChoice(parsedLine.referenceLabel)
            val child = ReferenceChoice(parsedLine.baseLabel, parsedLine.displayed, reference, possibleProperties = possibleProperties)
            previousChoices.last().children.add(child)
            previousChoices.add(child)
        } else {
            val child = StandardChoice(parsedLine.baseLabel, parsedLine.displayed, possibleProperties = possibleProperties)
            previousChoices.last().children.add(child)
            previousChoices.add(child)
        }
    }

    private fun findTopChoice(label: String): TopChoice {

        for (found in topChoices) {
            if (found.label == label) {
                return found
            }
        }
        throw RuntimeException("top level choice '$label' not found")
    }
}

fun main() {

    val topChoices = ChoicesParser().parse("src/main/resources/choices.txt")
    println("Done")
}