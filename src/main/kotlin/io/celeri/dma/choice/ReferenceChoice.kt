package io.celeri.dma.choice

class ReferenceChoice(
        label: String,
        children: List<StandardChoice>,
        displayed: Boolean,
        possibleParameters: List<TopChoice>,
        val reference: TopChoice) : StandardChoice(label, children, displayed, possibleParameters) {

}
