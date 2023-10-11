package com.github.davimc.picpay.entities.enums;

public enum PersonType {
    NATURAL(1, "Física"),
    LEGAL(2, "Jurídica");

    private Integer cod;
    private String discription;

    PersonType(Integer cod, String discription) {
        this.cod = cod;
        this.discription = discription;
    }

    public Integer getCod() {
        return cod;
    }

    public String getDiscription() {
        return discription;
    }

    public static PersonType toEnum(Integer cod) {
        if(cod == null) return null;
        for(PersonType x: PersonType.values())
            if(cod.equals(x.getCod()))
                return x;
        throw new IllegalArgumentException("Tipo inválido");
    }
}
