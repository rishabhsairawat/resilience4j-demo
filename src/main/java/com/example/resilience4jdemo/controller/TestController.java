package com.example.resilience4jdemo.controller;

import com.example.resilience4jdemo.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/** @author RishabhSairawat */
/**
 * This class represents a controller for handling test requests.
 */
@RestController
@RequestMapping("/test")
public class TestController {

  private final TestService testService;

  /**
   * Constructs a new TestController with the specified TestService.
   * 
   * @param testService the TestService to be used by the controller
   */
  public TestController(TestService testService) {
    this.testService = testService;
  }

  /**
   * Handles the GET request for "/test/fast" endpoint.
   * 
   * @return the response from the testService.fastResponse() method
   */
  @GetMapping("/fast")
  public String getAppFastResponse() {
    return testService.fastResponse();
  }

  /**
   * Handles the GET request for "/test/slow" endpoint.
   * 
   * @return the response from the testService.slowResponse() method
   */
  @GetMapping("/slow")
  public String getAppSlowResponse() {
    return testService.slowResponse();
  }

  /**
   * Handles the GET request for "/test/exception" endpoint.
   * 
   * @return the response from the testService.exceptionResponse() method
   */
  @GetMapping("/exception")
  public String getAppExceptionResponse() {
    return testService.exceptionResponse();
  }
}
