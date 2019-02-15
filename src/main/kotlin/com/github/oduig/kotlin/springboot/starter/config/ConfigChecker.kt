package com.github.oduig.kotlin.springboot.starter.config

import com.github.oduig.kotlin.springboot.starter.config.properties.StarterConfigProperties
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class ConfigChecker(private val starterConfigProperties: StarterConfigProperties) {

  @EventListener(ApplicationReadyEvent::class)
  fun checkConfig() {
    // Perform a basic check to make sure the configuration is loaded on startup
    requireNotNull(starterConfigProperties.cors)
  }
}
