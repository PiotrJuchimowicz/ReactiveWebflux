package com.company.imperative_api.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "CUSTOMER")
public class Customer extends AbstractEntity {

    private String name;
    private String surname;
    private String email;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Set<Invoice> invoices;

    private Customer() {
    }

    public Customer(UUID id, String name, String surname, String email, Set<Invoice> invoices) {
        super(id);
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.invoices = invoices;
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
