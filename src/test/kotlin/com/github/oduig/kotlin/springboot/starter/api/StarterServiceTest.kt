package com.github.oduig.kotlin.springboot.starter.api

import com.github.oduig.kotlin.springboot.starter.config.properties.StarterConfigProperties
import com.github.oduig.kotlin.springboot.starter.util.SoundSleeper
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class StarterServiceTest {

  private val starterConfigProperties = StarterConfigProperties(slothDelayMs = 100)

  private var soundSleeperMock = mockk<SoundSleeper>()

  private val starterService = StarterService(starterConfigProperties, soundSleeperMock)

  @Test
  fun starterServiceTest() {
    // given
    givenSoundSleeperSleepsFor(100)

    // when
    starterService.waitForABit()

    // then
    thenSoundSleeperSlept()
  }

  private fun givenSoundSleeperSleepsFor(expectedSleepDuration: Long) {
    every { soundSleeperMock.sleep(expectedSleepDuration) } returns expectedSleepDuration
  }

  private fun thenSoundSleeperSlept() {
    verify { soundSleeperMock.sleep(any()) }
  }
}
