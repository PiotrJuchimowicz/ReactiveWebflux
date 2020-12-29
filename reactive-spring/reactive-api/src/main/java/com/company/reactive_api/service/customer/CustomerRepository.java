package com.company.reactive_api.service.customer;

import com.company.reactive_api.model.customer.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, UUID> {

}
