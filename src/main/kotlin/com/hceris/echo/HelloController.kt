package com.hceris.echo

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
        return ResponseEntity.ok(Salutation("world"))
    }

    @GetMapping("{name}")
    @ApiOperation(value = "Greets you back")
    fun there(@ApiParam("Your name") @PathVariable("name") name: String): ResponseEntity<Salutation> {
        return ResponseEntity.ok(Salutation(name))
    }
}

data class Salutation(val name: String)
