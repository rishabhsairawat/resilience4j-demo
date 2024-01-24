package com.example.resilience4jdemo;

import io.github.resilience4j.bulkhead.BulkheadFullException;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.resilience4jdemo.helpers.HttpClient;
import com.example.resilience4jdemo.service.TestService;

import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestServiceTest {

    @Mock
    private HttpClient httpClient;

    @InjectMocks
    private TestService testService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFastResponse() {
        when(HttpClient.genericGet(any(String.class), any())).thenReturn(Mono.just("Fast response"));
        String response = testService.fastResponse();
        assertEquals("Fast response", response);
    }

    @Test
    public void testSlowResponse() {
        when(HttpClient.genericGet(any(String.class), any())).thenReturn(Mono.just("Slow response"));
        String response = testService.slowResponse();
        assertEquals("Slow response", response);
    }

    @Test
    public void testExceptionResponse() {
        when(HttpClient.genericGet(any(String.class), any())).thenReturn(Mono.just("Exception response"));
        String response = testService.exceptionResponse();
        assertEquals("Exception response", response);
    }

    @Test
    public void testBulkhead() {
        when(HttpClient.genericGet(any(String.class), any())).thenReturn(Mono.just("Fast response"));
        assertThrows(BulkheadFullException.class, () -> {
            for (int i = 0; i < 100; i++) {
                testService.fastResponse();
            }
        });
    }

    @Test
    public void testCircuitBreaker() {
        when(HttpClient.genericGet(any(String.class), any())).thenReturn(Mono.error(new RuntimeException()));
        assertThrows(CallNotPermittedException.class, () -> {
            for (int i = 0; i < 100; i++) {
                testService.exceptionResponse();
            }
        });
    }
}