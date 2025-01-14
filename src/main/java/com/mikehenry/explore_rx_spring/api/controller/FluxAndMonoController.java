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

    @GetMapping(value = "/flux-infinite-stream-with-conditions-and-retry", produces = MediaType.APPLICATION_NDJSON_VALUE)
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
