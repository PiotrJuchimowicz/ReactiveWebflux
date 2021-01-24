package com.company.imperative_client.client;

import com.company.dto.CustomerDto;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.math.BigDecimal;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.function.Consumer;

public class ImperativeClient implements Client {

    @SneakyThrows
    @Override
    public void post(CustomerDto body, String url, Consumer<BigDecimal> successFunction) {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper objectMapper = JsonMapperFactory.getJsonMapper();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .header("Content-Type", "application/json; charset=utf8")
                .POST(HttpRequest.BodyPublishers.ofString(objectMapper.writeValueAsString(body)))
                .build();

        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        successFunction.accept(new BigDecimal(httpResponse.body()));
    }

    @Override
    @SneakyThrows
    public void get(String url, Consumer<CustomerDto> successFunction) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(url))
                .GET()
                .build();

        ObjectMapper objectMapper = JsonMapperFactory.getJsonMapper();
        HttpResponse<String> httpResponse = client.send(request, HttpResponse.BodyHandlers.ofString());
        List<CustomerDto> customerDtoList = objectMapper.readValue(httpResponse.body(), new TypeReference<List<CustomerDto>>() {});
        customerDtoList.forEach(successFunction);
    }
}
