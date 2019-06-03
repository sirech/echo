package com.hceris.echo.fishes

import org.junit.jupiter.api.Test

internal class ParserTest {
    val parser = Parser("fishes.yaml".asStream().readTextAndClose())

    @Test
    fun `it works`() {
        parser.parsed
    }
}
