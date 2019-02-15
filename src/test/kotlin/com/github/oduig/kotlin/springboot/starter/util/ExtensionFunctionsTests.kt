package com.github.oduig.kotlin.springboot.starter.util

import org.hamcrest.Matchers.greaterThanOrEqualTo
import org.junit.Assert
import org.junit.Test

class ExtensionFunctionsTests() {

  @Test
  fun enumerable() {
    // given
    val numberList = listOf(1, 2, 3)

    // when
    val enumerator = numberList.enumerate()

    // then
    Assert.assertEquals(numberList, enumerator.toList())
  }
}
