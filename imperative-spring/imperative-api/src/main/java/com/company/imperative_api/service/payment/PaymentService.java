package com.company.imperative_api.service.payment;


import com.company.imperative_api.model.Customer;
import com.company.imperative_api.model.Invoice;
import com.company.imperative_api.model.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Payment savePayment(Customer customer) {
        final BigDecimal amount = customer.getInvoices().stream()
                .map(Invoice::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return paymentRepository.save(new Payment(UUID.randomUUID(), LocalDate.now(), amount));
    }
}
