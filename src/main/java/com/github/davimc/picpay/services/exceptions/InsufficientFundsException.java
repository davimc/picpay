package com.github.davimc.picpay.services.exceptions;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException() {
        super("Insufficient balance to complete the transfer");
    }
}
