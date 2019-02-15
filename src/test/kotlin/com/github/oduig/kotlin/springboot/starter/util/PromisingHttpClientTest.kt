package com.github.oduig.kotlin.springboot.starter.util

import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import io.mockk.verify
import org.junit.Assert
import org.junit.Test
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.Disposable
import reactor.core.publisher.Mono
import java.io.IOException
import java.util.function.Consumer
import kotlin.concurrent.thread

class PromisingHttpClientTest {

  companion object {
    private const val RESPONSE_DELAY_MS = 50L
  }

  private val mockGetRequestSpec = mockk<WebClient.RequestHeadersUriSpec<*>>()
  private val mockPostRequestSpec = mockk<WebClient.RequestBodyUriSpec>()
  private val mockResponseSpec = mockk<WebClient.ResponseSpec>()
  private val mockResponseMono = mockk<Mono<String>>()
  private val webClient = mockk<WebClient>()
  private val promisingHttpClient = PromisingHttpClient(webClient)

  @Test
  fun get_whenSuccessful() {
    // given
    val url = "http://example.com"
    givenSuccessfulGetRequest(url, "Hello.")

    // when
    val promise = promisingHttpClient.get(url)

    // then
    getRequestWasMade()
    Assert.assertEquals("Hello.", promise.get())
  }

  @Test(expected = IOException::class)
  fun get_whenFailed() {
    // given
    val url = "http://example.com"
    givenFailedGetRequest(url)

    // when
    val promise = promisingHttpClient.get(url)
    promise.get()

    // then
    // Error occurs
  }

  @Test
  fun post_whenSuccessful() {
    // given
    val url = "http://example.com"
    givenSuccessfulPostRequest(url, "Hello.")

    // when
    val promise = promisingHttpClient.post(url, "[]")

    // then
    postRequestWasMade()
    Assert.assertEquals("Hello.", promise.get())
  }

  @Test(expected = IOException::class)
  fun post_whenFailed() {
    // given
    val url = "http://example.com"
    givenFailedPostRequest(url)

    // when
    val promise = promisingHttpClient.post(url, "[]")
    promise.get()

    // then
    // Error occurs
  }

  private fun givenSuccessfulGetRequest(url: String, response: String) {
    every { mockGetRequestSpec.uri(url) } returns mockGetRequestSpec
    every { mockGetRequestSpec.accept(any()) } returns mockGetRequestSpec
    every { mockGetRequestSpec.retrieve() } returns mockResponseSpec
    val slot = slot<Consumer<String>>()
    every { mockResponseMono.subscribe(capture(slot), any()) } returns Disposable {}
    every { mockResponseSpec.bodyToMono(String::class.java) } returns mockResponseMono
    every { webClient.get() } returns mockGetRequestSpec
    thread {
      Thread.sleep(Companion.RESPONSE_DELAY_MS)
      slot.captured.accept(response)
    }
  }

  private fun givenFailedGetRequest(url: String) {
    every { mockGetRequestSpec.uri(url) } returns mockGetRequestSpec
    every { mockGetRequestSpec.accept(any()) } returns mockGetRequestSpec
    every { mockGetRequestSpec.retrieve() } throws IOException()
    every { webClient.get() } returns mockGetRequestSpec
    val slot = slot<Consumer<Throwable>>()
    every { mockResponseMono.subscribe(any(), capture(slot)) } returns Disposable {}
    every { mockResponseSpec.bodyToMono(String::class.java) } returns mockResponseMono
    every { webClient.get() } returns mockGetRequestSpec
    thread {
      Thread.sleep(Companion.RESPONSE_DELAY_MS)
      slot.captured.accept(Throwable())
    }
  }

  private fun givenSuccessfulPostRequest(url: String, response: String) {
    every { mockPostRequestSpec.uri(url) } returns mockPostRequestSpec
    every { mockPostRequestSpec.accept(any()) } returns mockPostRequestSpec
    every { mockPostRequestSpec.contentType(any()) } returns mockPostRequestSpec
    every { mockPostRequestSpec.body(any()) } returns mockPostRequestSpec
    every { mockPostRequestSpec.retrieve() } returns mockResponseSpec
    val slot = slot<Consumer<String>>()
    every { mockResponseMono.subscribe(capture(slot), any()) } returns Disposable {}
    every { mockResponseSpec.bodyToMono(String::class.java) } returns mockResponseMono
    every { webClient.post() } returns mockPostRequestSpec
    thread {
      Thread.sleep(10)
      slot.captured.accept(response)
    }
  }

  private fun givenFailedPostRequest(url: String) {
    every { mockPostRequestSpec.uri(url) } returns mockPostRequestSpec
    every { mockPostRequestSpec.accept(any()) } returns mockPostRequestSpec
    every { mockPostRequestSpec.contentType(any()) } returns mockPostRequestSpec
    every { mockPostRequestSpec.body(any()) } returns mockPostRequestSpec
    every { mockPostRequestSpec.retrieve() } throws IOException()
    every { webClient.post() } returns mockPostRequestSpec
  }

  private fun getRequestWasMade() {
    verify { webClient.get() }
  }

  private fun postRequestWasMade() {
    verify { webClient.post() }
  }
}
