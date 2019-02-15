package com.github.oduig.kotlin.springboot.starter.api

import com.github.oduig.kotlin.springboot.starter.config.properties.StarterConfigProperties
import com.github.oduig.kotlin.springboot.starter.util.SoundSleeper
import org.springframework.stereotype.Service

@Service
class StarterService(private val starterConfigProperties: StarterConfigProperties,
                     private val soundSleeper: SoundSleeper) {

  fun waitForABit() {
    soundSleeper.sleep(starterConfigProperties.slothDelayMs!!)
  }
}
