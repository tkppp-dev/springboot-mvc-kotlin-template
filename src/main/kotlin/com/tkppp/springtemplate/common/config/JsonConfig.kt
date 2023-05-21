package com.tkppp.springtemplate.common.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.kotlinModule
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JsonConfig {

    @Bean
    fun getObjectMapper(): ObjectMapper {
        return ObjectMapper().registerModule(kotlinModule())
    }
}