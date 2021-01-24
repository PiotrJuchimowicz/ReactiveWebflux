package com.company.imperative_client;

import com.company.dto.CustomerDto;
import com.company.dto.CustomerDtoFactory;
import com.company.imperative_client.client.Client;
import com.company.imperative_client.client.ImperativeClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ImperativeApp {

    private static final String API_URL = "http://localhost:8070";
    private static final String ADD_CUSTOMER_API = "/customers";
    private static final String LIST_CUSTOMERS_API = "/customers/list";
    private static final String CUSTOMERS_FILE_NAME = "customers_imperative.txt";
    private static final String AMOUNTS_FILE_NAME = "amounts_imperative.txt";

    @SneakyThrows
    public static void main(String[] args) {
        final int threads = 3;
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(threads);
        final Client webClient = new ImperativeClient();

        Runnable saveCustomer = () -> webClient
                .post(CustomerDtoFactory.newRandomCustomerDto(), API_URL + ADD_CUSTOMER_API, ImperativeApp::saveAmountToFile);
        scheduledThreadPool.scheduleAtFixedRate(saveCustomer, 50L, 200L, TimeUnit.MILLISECONDS);

        Runnable getCustomers = () -> webClient.get(API_URL + LIST_CUSTOMERS_API, ImperativeApp::saveCustomersToFile);
        scheduledThreadPool.scheduleAtFixedRate(getCustomers, 5000L, 200L, TimeUnit.MILLISECONDS);
    }

    @SneakyThrows
    private static void saveCustomersToFile(CustomerDto customerDto) {
        File file = new File(CUSTOMERS_FILE_NAME);
        FileUtils.writeStringToFile(file,
                "******" + customerDto.toString() + "\n",
                Charset.defaultCharset(), true);
    }

    @SneakyThrows
    private static void saveAmountToFile(BigDecimal amount) {
        File file = new File(AMOUNTS_FILE_NAME);
        FileUtils.writeStringToFile(file,
                "******" + amount + "\n",
                Charset.defaultCharset(), true);
    }
}
