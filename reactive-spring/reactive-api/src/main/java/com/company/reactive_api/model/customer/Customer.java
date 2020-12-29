package com.company.reactive_api.model.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;
import java.util.UUID;

@Document(collection = "customers")
public class Customer {

    @Id
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private Set<Invoice> invoices;

    private Customer() {
    }

    public Customer(UUID id, String name, String surname, String email, Set<Invoice> invoices) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.invoices = invoices;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public Set<Invoice> getInvoices() {
        return invoices;
    }
}
