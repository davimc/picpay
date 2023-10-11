package com.github.davimc.picpay.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;

@Entity
@DiscriminatorValue("N")
public class PersonNatural extends Person {

    private String firstName;
    private String lastName;
    private String cpf;

    public PersonNatural() {
    }

    public PersonNatural(String firstName, String lastName, String cpf) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }
    @Override
    public String getName() {
        return firstName;
    }

    @Override
    public void setName(String name) {
        firstName = name;
    }

    @Override
    public String getSecondName() {
        return lastName;
    }

    @Override
    public void setSecondName(String name) {
        lastName = name;
    }

    @Override
    public String getDocument() {
        return cpf;
    }

    @Override
    public void setDocument(String document) {
        cpf = document;

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
