package io.celeri.dma.parse

class ParsedLine(
    val level: Int,
    val baseLabel: String,
    val creation: Boolean,
    val displayed: Boolean,
    val reference: Boolean,
    val referenceLabel : String,
    val propertyLabels: List<String>) {

    companion object {

        private const val TAB = "\t"
        private const val PROPERTY_SEPARATOR = " : "
        private const val REFERENCE_SEPARATOR = " > "
        private const val CREATION_PREFIX = "*" // or DISPLAY_PREFIX, depending on context

        fun parsedLine(line: String): ParsedLine {

            if (line.isEmpty()) throw ParsingException("line is empty")

            val level = naiveCountTabsAtBeginning(line)
            var line = line.trim()

            val creation = level == 0 && line.startsWith(CREATION_PREFIX)
            // always displayed if on top
            // if not on top, displayed only if there's no prefix
            val displayed = if (level == 0) true else !line.startsWith(CREATION_PREFIX)
            line = line.removePrefix(CREATION_PREFIX)

            val reference = line.contains(REFERENCE_SEPARATOR)

            var baseLabel = ""
            var referenceLabel = ""
            var propertyLabels: List<String> = emptyList()

            separation(
                    line,
                    { label -> baseLabel = label },
                    { label -> referenceLabel = label },
                    { label -> propertyLabels = label })

            return ParsedLine(level, baseLabel, creation, displayed, reference, referenceLabel, propertyLabels)
        }

        private fun separation(
                line: String,
                baseLabelConsumer: (baseLabel: String) -> Unit,
                referenceLabelConsumer: (referenceLabel: String) -> Unit,
                propertiesLabelsConsumer: (propertiesLabels: List<String>) -> Unit) {

            if (line.contains(PROPERTY_SEPARATOR)) {
                val propertySplit = line.split(PROPERTY_SEPARATOR).toList()

                val baseLabelCandidate = propertySplit.first()
                if (baseLabelCandidate.isBlank()) throw ParsingException("not even base label; line: '$line'")

                providePropertyLabels(line, propertySplit, propertiesLabelsConsumer)

                if (baseLabelCandidate.contains(REFERENCE_SEPARATOR)) {
                    provideBaseAndReferenceLabels(baseLabelCandidate, baseLabelConsumer, referenceLabelConsumer)
                } else {
                    baseLabelConsumer(baseLabelCandidate)
                }

            } else if (line.contains(REFERENCE_SEPARATOR)) {
                propertiesLabelsConsumer(emptyList())
                provideBaseAndReferenceLabels(line, baseLabelConsumer, referenceLabelConsumer)

            } else {
                propertiesLabelsConsumer(emptyList())
                referenceLabelConsumer("")
                baseLabelConsumer(line)
            }
        }

        private fun providePropertyLabels(
                line: String,
                propertySplit: List<String>,
                propertiesLabelsConsumer: (propertiesLabels: List<String>) -> Unit) {

            val propertyLabels = propertySplit.subList(1, propertySplit.size)
            if (propertyLabels
                            .filter { it.isBlank() }
                            .isNotEmpty()) throw ParsingException("blank properties; line: '$line'")

            propertiesLabelsConsumer(propertyLabels)
        }

        private fun provideBaseAndReferenceLabels(
                line: String,
                baseLabelConsumer: (baseLabel: String) -> Unit,
                referenceLabelConsumer: (referenceLabel: String) -> Unit) {

            val referenceSplit = line.split(REFERENCE_SEPARATOR).toList()
            if (referenceSplit.size > 2) throw ParsingException("too many reference separators; line: '$line'")
            if (referenceSplit
                            .filter { it.isBlank() }
                            .isNotEmpty()) throw ParsingException("blank base or reference; line: '$line'")

            baseLabelConsumer(referenceSplit.first())
            referenceLabelConsumer(referenceSplit.last())
        }

        private fun naiveCountTabsAtBeginning(line: String): Int { // TODO replace with regeexp
            if (line.startsWith(
                    TAB.plus(TAB).plus(
                            TAB
                    ).plus(TAB).plus(TAB))) {
                return 5
            } else if (line.startsWith(
                    TAB.plus(TAB).plus(
                            TAB
                    ).plus(TAB))) {
                return 4
            } else if (line.startsWith(
                    TAB.plus(TAB).plus(
                            TAB
                    ))) {
                return 3
            } else if (line.startsWith(TAB.plus(TAB))) {
                return 2
            } else if (line.startsWith(TAB)) {
                return 1
            } else {
                return 0
            }
        }
    }
}

