package com.company.app.client;

import com.company.dto.CustomerDto;

import java.math.BigDecimal;
import java.util.function.Consumer;

public interface Client {

    void post(CustomerDto body, String url, Consumer<BigDecimal> successFunction);

    void get(String url, Consumer<CustomerDto> successFunction);
}
