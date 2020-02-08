package com.hceris.echo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val PATH = "/list"
private const val NUMBERS_DEFAULT = 5
private const val NUMBERS_MAX = 100

@RestController
@RequestMapping(PATH)
class ListController {
    @GetMapping("numbers")
    fun numbersDefault(): ResponseEntity<Numbers> {
        return ResponseEntity.ok(formatNumbers(NUMBERS_DEFAULT))
    }

    @GetMapping("numbers/{elements}")
    fun numbers(@PathVariable("elements") elements: Int): ResponseEntity<Numbers> {
        val range = elements.coerceAtMost(NUMBERS_MAX)
        return ResponseEntity.ok(formatNumbers(range))
    }

    fun formatNumbers(elements: Int) = Numbers((1..elements).toList())
}

data class Numbers(val elements: List<Number>)

