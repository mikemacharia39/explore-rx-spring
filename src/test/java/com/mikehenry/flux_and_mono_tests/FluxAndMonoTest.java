package com.mikehenry.flux_and_mono_tests;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class FluxAndMonoTest {

    @Test
    void fluxTest() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .concatWithValues("After error") // this will not be executed because of the error
                .log(); // to see the events

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
                .verifyComplete(); // this is the last step to verify the flux
    }

    @Test
    void fluxTestElements_WithoutError_UsingOneExpectNext() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .log(); // to see the logs

        StepVerifier.create(stringFlux)
                .expectNext("Spring", "Spring boot", "Reactive spring boo")
                .verifyComplete();
    }

    @Test
    void fluxTestElements_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .log(); // to see the logs

        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring boot")
                .expectNext("Reactive spring boo")
                //.expectError(RuntimeException.class) // this is the expected error
                .expectErrorMessage("Exception occurred")// this is the expected error message
                .verify();
    }

    @Test
    void fluxTestElementsCount_WithError() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring boot", "Reactive spring boo")
                .concatWith(Flux.error(new RuntimeException("Exception occurred")))
                .log(); // to see the logs

        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .expectError(RuntimeException.class) // this is the expected error
                .verify();
    }

    @Test
    void monoTest() {
        Mono<String> stringMono = Mono.just("Spring");

        StepVerifier.create(stringMono.log())
                .expectNext("Spring")
                .verifyComplete();
    }

    @Test
    void monoTest_WithError() {
        Mono<?> monoResponse = Mono.just("Spring")
                        .then(Mono.error(new RuntimeException("Error occurred")))
                                .log();

        StepVerifier.create(monoResponse)
                .expectError(RuntimeException.class)
                .verify();
    }
}