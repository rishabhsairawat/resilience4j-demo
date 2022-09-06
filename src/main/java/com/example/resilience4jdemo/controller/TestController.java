package com.example.resilience4jdemo.controller;

import com.example.resilience4jdemo.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author RishabhSairawat */
@RestController
@RequestMapping("/test")
public class TestController {

  private final TestService testService;

  public TestController(TestService testService) {
    this.testService = testService;
  }

  @GetMapping("/fast")
  public String getAppFastResponse() {
    return testService.fastResponse();
  }

  @GetMapping("/slow")
  public String getAppSlowResponse() {
    return testService.slowResponse();
  }

  @GetMapping("/exception")
  public String getAppExceptionResponse() {
    return testService.exceptionResponse();
  }
}
