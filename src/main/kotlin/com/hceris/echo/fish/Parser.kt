package com.hceris.echo.fish

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.readValue
import com.fasterxml.jackson.module.kotlin.registerKotlinModule

typealias FishList = List<Fish>
typealias Fish = Map<Language, String>

class Parser(private val value: String) {
    val parsed: FishList by lazy {
        parse()
    }

    private fun parse(): FishList {
        return mapper.readValue(content = value)
    }

    private val mapper: ObjectMapper by lazy {
        ObjectMapper(YAMLFactory())
                .registerKotlinModule()
    }
}
