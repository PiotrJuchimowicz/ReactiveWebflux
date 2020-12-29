package com.company.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class InvoiceDto {

    private String id;
    private String number;
    private BigDecimal amount;
    private LocalDateTime issueDate;
    private String description;

    private InvoiceDto() {
    }

    public InvoiceDto(String id, String number, BigDecimal amount, LocalDateTime issueDate, String description) {
        this.id = id;
        this.number = number;
        this.amount = amount;
        this.issueDate = issueDate;
        this.description = description;
    }

    public InvoiceDto(String number, BigDecimal amount, LocalDateTime issueDate, String description) {
        this.number = number;
        this.amount = amount;
        this.issueDate = issueDate;
        this.description = description;
    }

    public String getId() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvoiceDto that = (InvoiceDto) o;
        return Objects.equals(id, that.id) && Objects.equals(number, that.number) && Objects
                .equals(amount, that.amount) && Objects.equals(issueDate, that.issueDate) && Objects
                .equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, amount, issueDate, description);
    }

    @Override
    public String toString() {
        return "InvoiceDto{" +
                "id='" + id + '\'' +
                ", number='" + number + '\'' +
                ", amount=" + amount +
                ", issueDate=" + issueDate +
                ", description='" + description + '\'' +
                '}';
    }
}
