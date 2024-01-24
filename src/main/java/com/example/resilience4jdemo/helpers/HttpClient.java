package com.example.resilience4jdemo.helpers;

import java.net.URI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/** @author RishabhSairawat */
/**
 * A utility class for making HTTP requests.
 */
public class HttpClient {

  /**
   * Sends a GET request to the specified URI and retrieves the response body as a Mono.
   *
   * @param uriString      the URI string to send the request to
   * @param typeReference  the parameterized type reference representing the expected response type
   * @param <T>            the type of the response body
   * @return a Mono representing the response body
   */
  public static <T> Mono<T> genericGet(
      String uriString, ParameterizedTypeReference<T> typeReference) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(uriString);
    URI uri = uriBuilder.build().encode().toUri();
    return WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(typeReference);
  }
}
