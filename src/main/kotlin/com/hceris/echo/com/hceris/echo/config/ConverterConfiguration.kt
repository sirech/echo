package com.hceris.echo.com.hceris.echo.config

import com.hceris.echo.Name
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class ConverterConfiguration : WebMvcConfigurer {
    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(nameConverter())
    }

    private fun nameConverter(): Converter<String, Name> {
        return Converter<String, Name> { source -> Name.create(source) }
    }
}