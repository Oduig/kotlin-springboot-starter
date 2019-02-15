package com.github.oduig.kotlin.springboot.starter.api

import com.github.oduig.kotlin.springboot.starter.api.model.SimpleTextMessage
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class StarterController(private val starterService: StarterService) {

  @GetMapping("")
  fun home(): SimpleTextMessage {
    return SimpleTextMessage("Kotlin Spring Boot Starter is online!")
  }

  @GetMapping("/api/v1/sloth")
  // Enable web security for request authorization. Some business code should be written for this to work.
  // @PreAuthorize("hasPermission('TIMEKEEPING', 'READ')")
  fun sloth(): SimpleTextMessage {
    starterService.waitForABit()
    return SimpleTextMessage("...")
  }
}
