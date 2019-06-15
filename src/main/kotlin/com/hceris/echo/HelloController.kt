package com.hceris.echo

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private const val PATH = "/hello"

@RestController
@RequestMapping(PATH, produces = [MediaType.APPLICATION_JSON_VALUE])
@Api(value = "Hello There!", description = "This is a polite controller")
class HelloController {
    @GetMapping("")
    fun world(): ResponseEntity<Salutation> {
        return ResponseEntity.ok(Salutation(Name("world")))
    }

    @GetMapping("{name}")
    @ApiOperation(value = "Greets you back")
    fun there(@ApiParam("Your name") @PathVariable("name") name: Name): ResponseEntity<Salutation> {
        return ResponseEntity.ok(Salutation(name))
    }
}

data class Name(private val value: String) {
    companion object {
        @JvmStatic
        @JsonCreator
        fun create(value: String) = Name(value.capitalize())
    }

    @JsonValue
    override fun toString() = value
}

data class Salutation(val name: Name)
