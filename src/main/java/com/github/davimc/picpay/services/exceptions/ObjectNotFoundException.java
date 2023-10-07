package com.github.davimc.picpay.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }

    public ObjectNotFoundException(Long id,Class clazz) {
        super("ID: " + id + " não encontrado para a classe " + clazz.getSimpleName());
    }
}
