package com.github.davimc.picpay.DTO;

import com.github.davimc.picpay.entities.User;
import com.github.davimc.picpay.entities.UserLegal;
import com.github.davimc.picpay.entities.UserNatural;

public class UserDTO {
    private Long id;
    private String name;
    private String document;
    private String email;


    public UserDTO() {
    }

    public UserDTO(Long id, String name, String document,  String email) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.email = email;
    }
    public UserDTO(User obj) {
        this.id = obj.getId();
        this.email = obj.getEmail();
        if (obj instanceof UserNatural) {
            UserNatural objNatural = (UserNatural) obj;
            this.document = objNatural.getCpf();
            name = objNatural.getFirstName().concat(objNatural.getLastName());
        }
        else {
            UserLegal objLegal = (UserLegal) obj;
            this.document = objLegal.getCnpj();
            name = objLegal.getTradeName();
        }
    }
}
