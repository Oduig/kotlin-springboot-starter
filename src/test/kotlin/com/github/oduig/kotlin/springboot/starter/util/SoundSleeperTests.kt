package com.github.oduig.kotlin.springboot.starter.util

import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.hamcrest.Matchers.lessThan
import org.junit.Assert
import org.junit.Test
import kotlin.concurrent.thread

class SoundSleeperTests() {

  private val soundSleeper = SoundSleeper()

  @Test
  fun sleepSoundly() {
    // given
    val durationMillis = 100L

    // when
    val sleptDuration = soundSleeper.sleep(durationMillis)

    // then
    Assert.assertThat(sleptDuration, greaterThanOrEqualTo(durationMillis))
  }

  @Test
  fun sleepInterrupted() {
    // given
    val durationMillis = 100L
    val interruptAfterMillis = 10L
    val soundSleeperThread = Thread.currentThread()

    // when
    thread {
      soundSleeper.sleep(interruptAfterMillis)
      soundSleeperThread.interrupt()
    }
    val sleptDuration = soundSleeper.sleep(durationMillis)

    // then
    Assert.assertThat(sleptDuration, lessThan(durationMillis))
  }
}
