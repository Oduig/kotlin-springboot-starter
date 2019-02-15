package com.github.oduig.kotlin.springboot.starter.api

import io.mockk.*
import org.junit.Assert
import org.junit.Test

class StarterControllerTest {

  private var starterServiceMock = mockk<StarterService>()

  private val starterController = StarterController(starterServiceMock)

  @Test
  fun homeTest() {
    // given

    // when
    val reply = starterController.home()

    // then
    Assert.assertEquals("Kotlin Spring Boot Starter is online!", reply.message)
  }

  @Test
  fun slothTest() {
    // given
    givenStarterServiceWaits()

    // when
    val reply = starterController.sloth()

    // then
    Assert.assertEquals("...", reply.message)
    thenStarterServiceWaited()
  }

  private fun givenStarterServiceWaits() {
    every { starterServiceMock.waitForABit() } just Runs
  }

  private fun thenStarterServiceWaited() {
    verify { starterServiceMock.waitForABit() }
  }
}
