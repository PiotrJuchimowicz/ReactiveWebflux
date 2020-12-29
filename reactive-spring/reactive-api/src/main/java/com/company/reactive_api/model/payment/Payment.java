package com.company.reactive_api.model.payment;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Document(collection = "payments")
public class Payment {

    @Id
    private UUID id;
    private LocalDate paymentDate;
    private BigDecimal amount;


    private Payment() {
    }

    public Payment(UUID id, LocalDate paymentDate, BigDecimal amount) {
        this.id = id;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
