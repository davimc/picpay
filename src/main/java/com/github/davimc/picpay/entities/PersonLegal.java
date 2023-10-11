package com.github.davimc.picpay.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("L")
public class PersonLegal extends Person {

    private String tradeName;
    private String legalName;
    private String cnpj;

    public PersonLegal() {
    }

    public PersonLegal(String tradeName, String legalName, String cnpj) {
        this.tradeName = tradeName;
        this.legalName = legalName;
        this.cnpj = cnpj;
    }
    @Override
    public String getName() {
        return tradeName;
    }

    @Override
    public void setName(String name) {
        tradeName = name;
    }

    @Override
    public String getSecondName() {
        return legalName;
    }

    @Override
    public void setSecondName(String name) {
        legalName = name;
    }

    @Override
    public String getDocument() {
        return cnpj;
    }

    @Override
    public void setDocument(String document) {
        cnpj = document;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getLegalName() {
        return legalName;
    }

    public void setLegalName(String legalName) {
        this.legalName = legalName;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
