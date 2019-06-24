package com.hceris.echo.fish

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

enum class Language(private val language: String) {
    ES("es"),
    DE("de"),
    EN("en"),
    FR("fr"),
    JP("jp"),
    IT("it");

    companion object {
        @JvmStatic
        @JsonCreator
        fun fromString(s: String) = valueOf(s.toUpperCase())
    }

    @JsonValue
    fun asJson() = language
}

