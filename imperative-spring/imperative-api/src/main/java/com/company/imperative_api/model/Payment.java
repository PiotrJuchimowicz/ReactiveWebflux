package com.company.imperative_api.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "PAYMENT")
public class Payment extends AbstractEntity {

    private LocalDate paymentDate;
    private BigDecimal amount;

    private Payment() {
    }

    public Payment(UUID id, LocalDate paymentDate, BigDecimal amount) {
        super(id);
        this.paymentDate = paymentDate;
        this.amount = amount;
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
