package com.company.imperative_api.service.customer;

import com.company.dto.CustomerDto;
import com.company.imperative_api.model.Customer;
import com.company.imperative_api.model.Payment;
import com.company.imperative_api.service.payment.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PaymentService paymentService;

    @Transactional
    public BigDecimal save(CustomerDto customerDto) {
        final Customer customer = CustomerFactory.toEntity(customerDto);
        customerRepository.save(customer);
        final Payment payment = paymentService.savePayment(customer);
        return payment.getAmount();
    }

    @Transactional(readOnly = true)
    public List<CustomerDto> listCustomers() {
        return customerRepository.findAllCustomersWithInvoices()
                .stream()
                .map(CustomerFactory::toDto)
                .filter(customerDto -> !customerDto.getInvoiceDtos().isEmpty())
                .sorted(Comparator.comparing(customerDto -> customerDto.getInvoiceDtos().size()))
                .collect(Collectors.toList());
    }

}
