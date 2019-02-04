package com.hceris.echo

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE
import org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

const val PATH = "/hello"

@RestController
@RequestMapping(PATH)
class HelloController {
    @GetMapping("")
    fun world(): ResponseEntity<Salutation> {
        return ResponseEntity.ok(Salutation("world"))
    }

    @GetMapping("{name}")
    fun there(@PathVariable("name") name: String): ResponseEntity<Salutation> {
        return ResponseEntity.ok(Salutation(name))
    }
}

data class Salutation(val name: String)
