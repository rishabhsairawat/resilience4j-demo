package com.example.resilience4jdemo.helpers;

import java.net.URI;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

/** @author RishabhSairawat */
public class HttpClient {

  public static <T> Mono<T> genericGet(
      String uriString, ParameterizedTypeReference<T> typeReference) {
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(uriString);
    URI uri = uriBuilder.build().encode().toUri();
    return WebClient.builder().build().get().uri(uri).retrieve().bodyToMono(typeReference);
  }
}
