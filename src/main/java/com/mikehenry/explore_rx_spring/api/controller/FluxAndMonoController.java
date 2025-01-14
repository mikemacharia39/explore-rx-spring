package com.mikehenry.explore_rx_spring.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxAndMonoController {

    @GetMapping("/flux")
    public Flux<Integer> returnFlux() {
        return Flux.just(1, 2, 3, 4)
                .log();
    }

    @GetMapping(value = "/flux-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE) // MediaType.TEXT_EVENT_STREAM_VALUE is used for streaming in the browser
    public Flux<Integer> returnFluxStream() {
        return Flux.just(1, 2, 3, 4)
                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/flux-stream-with-back-pressure", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> createWithBackPressure() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureDrop() // this means the data is dropped when the subscriber is not able to keep up
                .log();
    }

    // if a consumer is not able to keep up, the data is buffered. the consumer can consume the data from the buffer
    @GetMapping(value = "/flux-stream-with-back-pressure-buffer", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> createWithBackPressureBuffer() {
        return Flux.interval(Duration.ofMillis(1))
                .onBackpressureBuffer(10) // this means the data is buffered when the subscriber is not able to keep up
                .log();
    }

    @GetMapping(value = "/flux-infinite-stream", produces = MediaType.APPLICATION_NDJSON_VALUE) // NDJSON = Newline Delimited JSON used for streaming. Replacement of APPLICATION_STREAM_JSON_VALUE
    public Flux<Long> returnInfiniteFluxStream() {
        return Flux.interval(Duration.ofSeconds(1))
                .log();
    }

    @GetMapping(value = "/flux-infinite-stream-with-conditions", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Long> returnInfiniteFluxStreamWithCancel() {
        return Flux.interval(Duration.ofSeconds(1))
                .switchMap(i -> {
                    if (i == 5) {
                        return Flux.error(new RuntimeException("Exception occurred"));
                    }
                    return Flux.just(i);
                })
                .log();
    }

    // Retry 2 times
    @GetMapping(value = "/flux-infinite-stream-with-conditions-and-retry", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Long> returnInfiniteFluxStreamWithCancelAndRetry() {
        return Flux.interval(Duration.ofSeconds(1))
                .switchMap(i -> {
                    if (i == 5) {
                        return Flux.error(new RuntimeException("Exception occurred"));
                    }
                    return Flux.just(i);
                })
                .retry(2)
                .log();
    }

    @GetMapping(value = "/mono", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Integer> returnMono() {
        return Mono.just(1)
                .log();
    }
}
