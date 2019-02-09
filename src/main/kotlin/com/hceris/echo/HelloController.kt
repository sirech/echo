package com.hceris.echo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

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
