package com.github.oduig.kotlin.springboot.starter.config.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "starter")
data class StarterConfigProperties(
    // These need to be nullable so Spring Boot can load them
    var slothDelayMs: Long? = null,
    var cors: CorsConfigProperties? = CorsConfigProperties(),
    var webClient: WebClientConfigProperties? = WebClientConfigProperties()
)
