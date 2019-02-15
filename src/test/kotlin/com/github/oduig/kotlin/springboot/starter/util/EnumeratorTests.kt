package com.github.oduig.kotlin.springboot.starter.util

import org.junit.Assert
import org.junit.Test

class EnumeratorTests {
  @Test
  fun enumerate() {
    // given
    val numberIterator = listOf(1, 2, 3).iterator()

    // when
    val numberEnumerator = Enumerator(numberIterator)

    // then
    Assert.assertEquals(1, numberEnumerator.current())
    Assert.assertEquals(1, numberEnumerator.current())
    numberEnumerator.moveNext()
    Assert.assertEquals(2, numberEnumerator.current())
    numberEnumerator.moveNext()
    Assert.assertEquals(3, numberEnumerator.current())
    Assert.assertEquals(3, numberEnumerator.current())
    numberEnumerator.moveNext()
    Assert.assertNull(numberEnumerator.current())
  }

  @Test
  fun toList_whenEmpty() {
    // given
    val originalList = listOf<Int>()
    val numberIterator = originalList.iterator()

    // when
    val resultList = Enumerator(numberIterator).toList()

    // then
    Assert.assertEquals(originalList, resultList)
  }

  @Test
  fun toList_whenFull() {
    // given
    val originalList = listOf(1, 2, 3)
    val numberIterator = originalList.iterator()

    // when
    val resultList = Enumerator(numberIterator).toList()

    // then
    Assert.assertEquals(originalList, resultList)
  }
}