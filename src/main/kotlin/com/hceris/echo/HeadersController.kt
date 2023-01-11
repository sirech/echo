package com.hceris.echo

import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val PATH = "/headers"

@RestController
@RequestMapping(PATH, produces = [MediaType.APPLICATION_JSON_VALUE])
class HeadersController {
    @GetMapping("")
    fun headers(@RequestHeader headers: HttpHeaders): ResponseEntity<Map<String, HeaderValue>> {
        return ResponseEntity.ok(format(headers))
    }

    private fun format(rawHeaders: HttpHeaders): Map<String, HeaderValue> {
        return rawHeaders.map { (key, value) ->
            val headerValue = when (value.size) {
                in 0..1 -> HeaderValue.SingleValue(value[0] ?: "")
                else -> HeaderValue.ListValue(value)
            }
            key to headerValue
        }.toMap()
    }
}

sealed class HeaderValue {
    data class SingleValue(@JsonValue private val value: String) : HeaderValue()
    data class ListValue(@JsonValue private val value: List<String>) : HeaderValue()
}
