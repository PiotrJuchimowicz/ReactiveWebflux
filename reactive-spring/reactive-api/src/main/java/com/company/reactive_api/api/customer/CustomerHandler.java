package com.company.reactive_api.api.customer;

import com.company.dto.CustomerDto;
import com.company.reactive_api.model.customer.Customer;
import com.company.reactive_api.service.customer.CustomerService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
class CustomerHandler {

    private final CustomerService customerService;

    Mono<ServerResponse> saveCustomer(ServerRequest request) {
        Mono<CustomerDto> customerDtoMono = request.bodyToMono(CustomerDto.class);
        Mono<BigDecimal> amountMono = customerService.save(customerDtoMono);
        return amountMono.flatMap(amount -> ServerResponse.ok().bodyValue(amount));
    }

    Mono<ServerResponse> listCustomers() {
        Flux<CustomerDto> customerDtos = customerService.listCustomers();
        return ServerResponse.ok().body(customerDtos, Customer.class);
    }
}
