package io.celeri.dma.parse

import java.io.FileInputStream

class SuggestionParser(
        private val verbsFilePath: String,
        private val rewardsFilePath: String,
        private val threatsFilePath: String) { // TODO change from String to Path, make sure it loads from outside of JAR

    private var verbs: List<String> = emptyList()
    private var rewards: List<String> = emptyList()
    private var threats: List<String> = emptyList()

    fun parse(consumer: (verbs: List<String>, rewards: List<String>, threats: List<String>) -> Unit) {

        verbs = emptyList()
        rewards = emptyList()
        threats = emptyList()

        verbs = FileInputStream(verbsFilePath).bufferedReader().use { it.readLines() }
        rewards = FileInputStream(rewardsFilePath).bufferedReader().use { it.readLines() }
        threats = FileInputStream(threatsFilePath).bufferedReader().use { it.readLines() }

        consumer(verbs, rewards, threats)
    }
}
