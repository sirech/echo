package com.hceris.echo.fish

import org.junit.jupiter.api.Test

internal class ParserTest {
    val parser = Parser("fish.yaml".asStream().readTextAndClose())

    @Test
    fun `it works`() {
        parser.parsed
    }
}
