package com.mikehenry.explore_rx_spring.api.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@Slf4j
@Component
public class SampleHandlerFunction {
    // it is a mono server response because it represents 1 server response
    public Mono<ServerResponse> serverResponseFlux(ServerRequest serverRequest) {
        log.info("request: {}", serverRequest.toString());
        return ServerResponse
                .ok()
                .contentType(MediaType.TEXT_EVENT_STREAM) // because it is a stream of events
                .body(
                        Flux.just(1, 2, 3, 4)
                                .delayElements(Duration.ofSeconds(1))
                                .log(), Integer.class
                );
    }

    public Mono<ServerResponse> serverResponseMono(ServerRequest serverRequest) {
        log.info("requestPath: {}", serverRequest.requestPath());
        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(
                        Mono.just(1)
                                .log(), Integer.class
                );
    }
}
