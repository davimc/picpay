package com.github.davimc.picpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("NATURAL")
public class UserNatural extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String firstName;

    private String lastName;

    @Column(length = 11)
    private String cpf;

    public UserNatural() {
    }

    public UserNatural(String email, String password, String firstName, String lastName, String cpf) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
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
