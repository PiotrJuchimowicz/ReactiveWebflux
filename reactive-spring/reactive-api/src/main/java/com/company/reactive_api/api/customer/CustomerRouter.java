package com.company.reactive_api.api.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@RequiredArgsConstructor
class CustomerRouter {

    private final CustomerHandler customerHandler;

    @Bean
    RouterFunction<ServerResponse> customers() {
        return route()
                .POST("/customers", accept(MediaType.APPLICATION_JSON), customerHandler::saveCustomer)
                .GET("/customers/list", serverRequest -> customerHandler.listCustomers())
                .build();
    }

    @Bean
    RouterFunction<ServerResponse> helloWorld() {
        return route(GET("/hello-world"), request -> ServerResponse.ok().bodyValue("Hello World"));
    }
}
