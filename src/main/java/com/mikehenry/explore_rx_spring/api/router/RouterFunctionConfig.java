package com.mikehenry.explore_rx_spring.api.router;

import com.mikehenry.explore_rx_spring.api.handler.SampleHandlerFunction;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> route(SampleHandlerFunction sampleHandlerFunction) {
        return RouterFunctions
                .route()
                .GET("/functional/flux", accept(MediaType.APPLICATION_JSON), sampleHandlerFunction::serverResponseFlux)
                .GET("/functional/mono", sampleHandlerFunction::serverResponseMono)
                .build();
    }
}
