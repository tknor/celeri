package io.celeri.rest

import io.celeri.dma.Suggestions
import io.celeri.dma.dto.SuggestionCountDto
import io.celeri.dma.dto.SuggestionsDto
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/dma/suggestions")
@CrossOrigin
class DmaSuggestionApi(private val suggestions: Suggestions) {

    @GetMapping("/reload")
    fun reloadChoices() {
        suggestions.loadSuggestions()
    }

    @PostMapping("/suggest")
    fun suggestions(@RequestBody countDto: SuggestionCountDto): SuggestionsDto {
        return SuggestionsDto(suggestions.suggestions(countDto.count))
    }
}
