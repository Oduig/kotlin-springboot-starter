package com.github.oduig.kotlin.springboot.starter.config.properties

data class WebClientConfigProperties(
    var connectTimeoutMs: Int? = null,
    var requestTimeoutSeconds: Int? = null
)
