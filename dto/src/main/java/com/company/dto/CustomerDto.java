package com.company.dto;

import java.util.Objects;
import java.util.Set;

public class CustomerDto {

    private String id;
    private String name;
    private String surname;
    private String email;
    private Set<InvoiceDto> invoiceDtos;

    public CustomerDto() {
    }

    public CustomerDto(String id, String name, String surname, String email,
                       Set<InvoiceDto> invoiceDtos) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.invoiceDtos = invoiceDtos;
    }

    public CustomerDto(String name, String surname, String email, Set<InvoiceDto> invoiceDtos) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.invoiceDtos = invoiceDtos;
    }

    public String getId() {
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

    public Set<InvoiceDto> getInvoiceDtos() {
        return invoiceDtos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(surname, that.surname) &&
                Objects.equals(email, that.email) &&
                Objects.equals(invoiceDtos, that.invoiceDtos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, email, invoiceDtos);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", invoiceDtos=" + invoiceDtos +
                '}';
    }
}
