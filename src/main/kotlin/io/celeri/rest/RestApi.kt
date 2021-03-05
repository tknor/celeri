package io.celeri.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RestApi {

    @GetMapping("/")
    fun hello(): String {
        return "Celeri"
    }
}
