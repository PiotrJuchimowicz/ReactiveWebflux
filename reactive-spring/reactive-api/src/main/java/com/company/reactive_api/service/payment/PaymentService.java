package com.company.reactive_api.service.payment;

import com.company.reactive_api.model.customer.Customer;
import com.company.reactive_api.model.customer.Invoice;
import com.company.reactive_api.model.payment.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    public Mono<Payment> savePayment(Customer customer) {
        final BigDecimal amount = customer.getInvoices().stream()
                .map(Invoice::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return paymentRepository.save(new Payment(UUID.randomUUID(), LocalDate.now(), amount));
    }
}
