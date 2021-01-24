package com.company.reactive_api.service.customer;

import com.company.dto.CustomerDto;
import com.company.reactive_api.model.customer.Customer;
import com.company.reactive_api.model.payment.Payment;
import com.company.reactive_api.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Comparator;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PaymentService paymentService;

    public Mono<BigDecimal> save(Mono<CustomerDto> customerDto) {
        return customerDto
                .map(CustomerFactory::toEntity)
                .flatMap(customerRepository::save)
                .flatMap(paymentService::savePayment)
                .map(Payment::getAmount);
    }

    public Flux<CustomerDto> listCustomers() {
        return customerRepository.findAll()
                .map(CustomerFactory::toDto)
                .filter(customerDto -> !customerDto.getInvoiceDtos().isEmpty())
                .sort(Comparator.comparing(customerDto -> customerDto.getInvoiceDtos().size()));
    }

}
