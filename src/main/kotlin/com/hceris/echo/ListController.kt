package com.hceris.echo

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val PATH = "/list"

@RestController
@RequestMapping(PATH)
class ListController {
    @GetMapping("numbers")
    fun numbersDefault(): ResponseEntity<Numbers> {
        return ResponseEntity.ok(formatNumbers(5))
    }

    @GetMapping("numbers/{elements}")
    fun numbers(@PathVariable("elements") elements: Int): ResponseEntity<Numbers> {
        val range = Math.min(elements, 100)
        return ResponseEntity.ok(formatNumbers(range))
    }

    fun formatNumbers(elements: Int) = Numbers((1..elements).toList())
}

data class Numbers(val elements: List<Number>)

