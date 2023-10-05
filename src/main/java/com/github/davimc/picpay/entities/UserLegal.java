package com.github.davimc.picpay.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.io.Serializable;

@Entity
@DiscriminatorValue("LEGAL")
public final class UserLegal extends User implements Serializable {
    private static final long serialVersioUID = 1L;

    private String tradeName;
    private String legalName;
    @Column(length = 14)
    private String cnpj;

    public UserLegal() {
    }

    public UserLegal(String email, String password, String tradeName, String legalName, String cnpj) {
        super(email, password);
        this.tradeName = tradeName;
        this.legalName = legalName;
        this.cnpj = cnpj;
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
