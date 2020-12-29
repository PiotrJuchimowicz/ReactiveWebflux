package com.company.reactive_api.model.customer;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Invoice {

    private UUID id;
    private String number;
    private BigDecimal amount;
    private LocalDateTime issueDate;
    private String description;

    private Invoice() {
    }

    public Invoice(UUID id, String number, BigDecimal amount, LocalDateTime issueDate, String description) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.issueDate = issueDate;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public String getDescription() {
        return description;
    }
}
