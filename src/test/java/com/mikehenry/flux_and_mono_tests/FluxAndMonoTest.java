package com.mikehenry.flux_and_mono_tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class FluxAndMonoTest {

    @Test
    void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .log(); // to see the logs

        stringFlux
                .subscribe(System.out::println,
                        e -> System.err.println("Exception is: " + e), // in case of error, this will be executed
                        () -> System.out.println("Completed")); // this will be executed when the flux is completed and when there is no error
    }

    @Test
    void fluxTestElements_WithoutError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .log(); // to see the logs

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive spring boo")
                .verifyComplete();
    }

    @Test
    void fluxTestElements_WithoutError_UsingOneExpectNext() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .log(); // to see the logs

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring boot", "Reactive spring boo")
                .verifyComplete();
    }
}