package com.company.reactive_api.service.customer;

import com.company.dto.CustomerDto;
import com.company.dto.InvoiceDto;
import com.company.reactive_api.model.customer.Customer;
import com.company.reactive_api.model.customer.Invoice;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

class CustomerFactory {

    private CustomerFactory() {
    }

    static CustomerDto toDto(Customer customer) {
        final Set<InvoiceDto> invoiceDtos = customer.getInvoices().stream()
                .map(invoice -> new InvoiceDto(invoice.getId().toString(), invoice.getNumber(), invoice.getAmount(), invoice.getIssueDate(),
                        invoice.getDescription()))
                .collect(Collectors.toSet());
        return new CustomerDto(customer.getId().toString(), customer.getName(), customer.getSurname(),
                customer.getEmail(), invoiceDtos);

    }

    static Customer toEntity(CustomerDto customerDto) {
        final UUID id = customerDto.getId() != null ? UUID.fromString(customerDto.getId()) : UUID.randomUUID();
        final Set<Invoice> invoices = customerDto.getInvoiceDtos().stream()
                .map(invoiceDto -> {
                    final UUID invoiceId =
                            invoiceDto.getId() != null ? UUID.fromString(invoiceDto.getId()) : UUID.randomUUID();
                    return new Invoice(invoiceId, invoiceDto.getNumber(), invoiceDto.getAmount(),
                            invoiceDto.getIssueDate(), invoiceDto.getDescription());
                })
                .collect(Collectors.toSet());
        return new Customer(id, customerDto.getName(), customerDto.getSurname(), customerDto.getEmail(), invoices);
    }
}
