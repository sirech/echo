package com.hceris.echo.com.hceris.echo.config

import com.hceris.echo.fishes.Parser
import com.hceris.echo.fishes.asStream
import com.hceris.echo.fishes.readTextAndClose
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FishConfiguration {
    @Bean
    fun fishList() = Parser("fishes.yaml".asStream().readTextAndClose()).parsed
}
