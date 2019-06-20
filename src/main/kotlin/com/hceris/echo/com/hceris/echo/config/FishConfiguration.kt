package com.hceris.echo.com.hceris.echo.config

import com.hceris.echo.fish.Parser
import com.hceris.echo.fish.asStream
import com.hceris.echo.fish.readTextAndClose
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FishConfiguration {
    @Bean
    fun fishList() = Parser("fish.yaml".asStream().readTextAndClose()).parsed
}
