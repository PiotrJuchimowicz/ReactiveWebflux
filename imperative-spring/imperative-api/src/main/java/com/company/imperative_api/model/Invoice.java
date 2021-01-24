package com.company.imperative_api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "INVOICE")
public class Invoice extends AbstractEntity {


    private String number;
    private BigDecimal amount;
    private LocalDateTime issueDate;
    private String description;

    private Invoice() {
    }

    public Invoice(UUID id, String number, BigDecimal amount, LocalDateTime issueDate, String description) {
        super(id);
        this.number = number;
        this.amount = amount;
        this.issueDate = issueDate;
        this.description = description;
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
