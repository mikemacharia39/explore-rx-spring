package com.mikehenry.flux_and_mono_tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;

class FluxAndMonoTest {

    @Test
    void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
//                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .log(); // to see the logs

        stringFlux
                .subscribe(System.out::println,
                        e -> System.err.println("Exception is: " + e), // in case of error, this will be executed
                        () -> System.out.println("Completed")); // this will be executed when the flux is completed and when there is no error
    }
}