package com.github.oduig.kotlin.springboot.starter.config

import com.github.oduig.kotlin.springboot.starter.config.properties.CorsConfigProperties
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class CorsConfiguration(private val corsConfigProperties: CorsConfigProperties): WebMvcConfigurer {

  override fun addCorsMappings(registry: CorsRegistry) {
    corsConfigProperties.routes.forEach {
      registry.addMapping(it)
          .allowedOrigins(*corsConfigProperties.origins.toTypedArray())
          .allowedMethods(*corsConfigProperties.methods.toTypedArray())
          .exposedHeaders("Authorization")
          .allowCredentials(true)
    }
  }
}
