package com.mikehenry.flux_and_mono_tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxAndMonoTest {

    @Test
    void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .log();

        stringFlux
                .subscribe(System.out::println);
    }
}
