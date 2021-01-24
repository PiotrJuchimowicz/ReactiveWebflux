package com.company.app;

import com.company.app.client.Client;
import com.company.app.client.ReactiveClient;
import com.company.dto.CustomerDto;
import com.company.dto.CustomerDtoFactory;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReactiveApp {

    private static final String API_URL = "http://localhost:8080";
    private static final String ADD_CUSTOMER_API = "/customers";
    private static final String LIST_CUSTOMERS_API = "/customers/list";
    private static final String CUSTOMERS_FILE_NAME = "customers_reactive.txt";
    private static final String AMOUNTS_FILE_NAME = "amounts_reactive.txt";

    public static void main(String[] args) {
        final int threads = 3;
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(threads);
        final Client webClient = new ReactiveClient();

        Runnable saveCustomer = () -> webClient
                .post(CustomerDtoFactory.newRandomCustomerDto(), API_URL + ADD_CUSTOMER_API, ReactiveApp::saveAmountToFile);
        scheduledThreadPool.scheduleAtFixedRate(saveCustomer, 50L, 200L, TimeUnit.MILLISECONDS);

        Runnable getCustomers = () -> webClient.get(API_URL + LIST_CUSTOMERS_API, ReactiveApp::saveCustomersToFile);
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
