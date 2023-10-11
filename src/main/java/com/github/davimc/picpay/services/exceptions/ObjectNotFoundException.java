package com.github.davimc.picpay.services.exceptions;

public class ObjectNotFoundException extends RuntimeException{
    public ObjectNotFoundException(String message) {
        super(message);
    }
    public ObjectNotFoundException(Long id, Class<?> clazz) {
        super("Id: " + id + " não encontrado para a classe: " + clazz.getSimpleName());
    }
    public ObjectNotFoundException(String identificador, Class<?> clazz) {
        super("Identificador: " + identificador + " não encontrado para a classe: " + clazz.getSimpleName());
    }
}
