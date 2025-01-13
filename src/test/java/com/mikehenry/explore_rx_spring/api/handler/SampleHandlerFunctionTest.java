package com.mikehenry.explore_rx_spring.api.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient // we need because in our implementation we are using functional endpoints. The functional approach is not enabled by default
class SampleHandlerFunctionTest {

    @Autowired
    WebTestClient webTestClient;

    @Test
    void testFluxApproach() {
        webTestClient.get().uri("/functional/flux")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBodyList(Integer.class)
                .hasSize(4);
    }

    @Test
    void testMonoApproach() {
        webTestClient.get().uri("/functional/mono")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType("application/json")
                .expectBody(Integer.class)
                .consumeWith(response ->
                        Assertions.assertEquals(1, response.getResponseBody()));
    }
}
