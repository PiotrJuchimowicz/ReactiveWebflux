package com.company.imperative_api.service.customer;

import com.company.imperative_api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    @Query("SELECT customer From Customer customer JOIN FETCH customer.invoices")
    List<Customer> findAllCustomersWithInvoices();
}
