package com.company.dto;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CustomerDtoFactory {

    private CustomerDtoFactory() {

    }

    public static CustomerDto newRandomCustomerDto() {
        Set<InvoiceDto> invoiceDtos = new HashSet<>();
        Random random = new Random(System.currentTimeMillis());
        for(int i = 0; i < random.nextInt(5); i++) {
            final InvoiceDto invoiceDto =  new InvoiceDto(RandomStringUtils.randomAlphanumeric(7), BigDecimal.valueOf(Math.random() * 100 + 2),
                    LocalDateTime.now(), RandomStringUtils.randomAlphanumeric(20));
            invoiceDtos.add(invoiceDto);
        }

        return new CustomerDto(RandomStringUtils.randomAlphanumeric(5),
                RandomStringUtils.randomAlphanumeric(5), RandomStringUtils.randomAlphanumeric(5), invoiceDtos);
    }
}
