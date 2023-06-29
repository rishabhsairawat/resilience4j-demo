package com.example.resilience4jdemo.service;

import com.example.resilience4jdemo.helpers.HttpClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/** @author RishabhSairawat */
@Service
@CircuitBreaker(name = "testService")
@Bulkhead(name = "testService")
public class TestService {

  private static final String appBaseUrl = "http://localhost:9999";

  public String fastResponse() {
    Mono<String> response =
        HttpClient.genericGet(appBaseUrl + "/fast", new ParameterizedTypeReference<>() {});
    return response.block();
  }

  public String slowResponse() {
    Mono<String> response =
        HttpClient.genericGet(appBaseUrl + "/slow", new ParameterizedTypeReference<>() {});
    return response.block();
  }

  @CircuitBreaker(name = "testServiceExceptionAPI")
  public String exceptionResponse() {
    Mono<String> response =
        HttpClient.genericGet(appBaseUrl + "/exception", new ParameterizedTypeReference<>() {});
    return response.block();
  }
}
