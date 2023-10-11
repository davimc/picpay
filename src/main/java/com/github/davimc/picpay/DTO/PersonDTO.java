package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.Person;
import com.github.davimc.picpay.entities.PersonLegal;
import com.github.davimc.picpay.entities.PersonNatural;

import java.io.Serial;
import java.io.Serializable;

public class PersonDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private String fOrTName;
    private String lOrLName;
    private String document;


    public PersonDTO() {
    }

    public PersonDTO(Person obj) {
        id = obj.getId();
        fOrTName = obj.getName();
        lOrLName = obj.getSecondName();
        document = obj.getDocument();
    }

    public Long getId() {
        return id;
    }

    public String getfOrTName() {
        return fOrTName;
    }

    public String getlOrLName() {
        return lOrLName;
    }

    public String getDocument() {
        return document;
    }
}
