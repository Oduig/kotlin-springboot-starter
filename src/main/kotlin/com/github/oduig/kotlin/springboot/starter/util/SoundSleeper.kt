package com.github.oduig.kotlin.springboot.starter.util

import mu.KLogging
import org.springframework.stereotype.Service

@Service
class SoundSleeper {

  companion object: KLogging()

  fun sleep(milliseconds: Long): Long {
    val t0 = System.currentTimeMillis()
    try {
      Thread.sleep(milliseconds)
    } catch (exception: InterruptedException) {
      logger.error { exception }
    }
    return System.currentTimeMillis() - t0
  }
}
