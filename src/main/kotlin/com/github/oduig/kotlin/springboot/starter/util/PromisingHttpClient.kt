package com.github.oduig.kotlin.springboot.starter.util

import com.fasterxml.jackson.databind.ObjectMapper
import mu.KLogging
import nl.komponents.kovenant.Kovenant
import nl.komponents.kovenant.Promise
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Component
class PromisingHttpClient(private val springWebClient: WebClient) {

  companion object: KLogging()

  fun get(uri: String): Promise<String, Throwable> {
    val request = springWebClient.get()
        .uri(uri)
        .accept(MediaType.APPLICATION_JSON_UTF8)

    return send(request)
  }

  fun post(uri: String, jsonBody: String): Promise<String, Throwable> {
    val request = springWebClient.post()
        .uri(uri)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .body(BodyInserters.fromObject(jsonBody))
        .accept(MediaType.ALL)

    return send(request)
  }

  private fun send(request: WebClient.RequestHeadersSpec<*>): Promise<String, Throwable> {
    val deferred = Kovenant.deferred<String, Throwable>()
    val futureResponse: Mono<String> = request.retrieve().bodyToMono(String::class.java)
    val handleSuccess = deferred::resolve
    val handleFailure = deferred::reject
    // It says this next line is not test-covered, but it is.
    futureResponse.subscribe(handleSuccess, handleFailure)
    return deferred.promise
  }
}
