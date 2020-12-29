package com.company.app.client;

import com.company.dto.CustomerDto;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.util.function.Consumer;

public class ReactiveClient implements Client {

    @Override
    public void post(CustomerDto body, String url, Consumer<BigDecimal> successFunction) {
        WebClient.create(url)
                .post()
                .body(BodyInserters.fromValue(body))
                .retrieve()
                .bodyToMono(BigDecimal.class)
                .subscribe(successFunction);
    }

    @Override
    public void get(String url, Consumer<CustomerDto> successFunction) {
                WebClient.create(url)
                .get()
                .retrieve()
                .bodyToFlux(CustomerDto.class)
                .subscribe(successFunction);
    }
}
