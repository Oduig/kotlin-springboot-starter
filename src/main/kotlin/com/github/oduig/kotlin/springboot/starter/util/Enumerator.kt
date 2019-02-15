package com.github.oduig.kotlin.springboot.starter.util

/**
 * Transforms an Iterator into a more convenient syntax,
 * so you can poll the current element multiple times without keeping your own variable.
 */
class Enumerator<T>(private val iterator: Iterator<T>) {

  private var current: T? = null

  init {
    moveNext()
  }

  fun current(): T? = this.current

  fun moveNext() {
    if (iterator.hasNext()) {
      current = iterator.next()
    } else {
      current = null
    }
  }

  fun toList(): List<T> {
    return if (current == null) emptyList()
      else listOf(current!!) + iterator.asSequence().toList()
  }
}
