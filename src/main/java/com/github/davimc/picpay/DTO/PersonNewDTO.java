package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.Person;
import com.github.davimc.picpay.entities.PersonLegal;
import com.github.davimc.picpay.entities.PersonNatural;
import com.github.davimc.picpay.entities.enums.PersonType;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

public class PersonNewDTO {

    @NotNull(message = "Name is required")
    private String name;
    @NotNull(message = "second name is required")
    private String secondName;
    @NotNull(message = "Document is required")
    // TODO resolver problema de validação
    //@CPF(message = "CPF format invalid")
    //@CNPJ(message = "CNPJ format invalid")
    private String document;
    @NotNull(message = "Type is required")
    private int type;

    public PersonNewDTO() {
    }

    public PersonNewDTO(String name, String secondName, String document, int type) {
        this.name = name;
        this.secondName = secondName;
        this.document = document;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Person toEntity() {
        Person obj = (PersonType.toEnum(type) == PersonType.NATURAL) ? new PersonNatural(): new PersonLegal();
        obj.setDocument(document);
        obj.setName(name);
        obj.setSecondName(secondName);

        return obj;
    }
}
