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

public class App {

    private static final String API_URL = "http://localhost:8080";
    private static final String ADD_CUSTOMER_API = "/customers";
    private static final String LIST_CUSTOMERS_API = "/customers/list";
    private static final String CUSTOMERS_FILE_NAME = "customers.txt";
    private static final String AMOUNTS_FILE_NAME = "amounts.txt";

    public static void main(String[] args) {
        final int threads = Runtime.getRuntime().availableProcessors();
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(threads);
        final Client webClient = new ReactiveClient();

        Runnable saveCustomer = () -> webClient
                .post(CustomerDtoFactory.newRandomCustomerDto(), API_URL + ADD_CUSTOMER_API, App::saveAmountToFile);
        scheduledThreadPool.scheduleAtFixedRate(saveCustomer, 50L, 50L, TimeUnit.MILLISECONDS);

        Runnable getCustomers = () -> webClient.get(API_URL + LIST_CUSTOMERS_API, App::saveCustomersToFile);
        scheduledThreadPool.scheduleAtFixedRate(getCustomers, 5000L, 100L, TimeUnit.MILLISECONDS);
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
