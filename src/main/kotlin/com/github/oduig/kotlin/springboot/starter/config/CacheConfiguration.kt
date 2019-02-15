package com.github.oduig.kotlin.springboot.starter.config

import com.github.benmanes.caffeine.cache.Caffeine
import com.github.benmanes.caffeine.cache.Ticker
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
@EnableCaching
class CacheConfiguration {

  companion object {
    const val CACHE_EXAMPLE_ID = "cacheExampleId"
  }

  @Bean
  fun cacheManager(ticker: Ticker): CacheManager {
    val manager = SimpleCacheManager()
    manager.setCaches(listOf(
        buildCache(CACHE_EXAMPLE_ID, ticker, 5, TimeUnit.MINUTES)
    ))
    return manager
  }

  private fun buildCache(name: String, ticker: Ticker, timeToExpire: Long, timeUnit: TimeUnit): CaffeineCache {
    return CaffeineCache(name, Caffeine.newBuilder()
        .expireAfterWrite(timeToExpire, timeUnit)
        .ticker(ticker)
        .build())
  }

  @Bean
  fun ticker(): Ticker {
    return Ticker.systemTicker()
  }
}
