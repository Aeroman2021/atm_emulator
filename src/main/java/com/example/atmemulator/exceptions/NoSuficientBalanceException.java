package com.example.atmemulator.exceptions;

public class NoSuficientBalanceException extends AccountException{
    public NoSuficientBalanceException() {
    }

    public NoSuficientBalanceException(String message) {
        super(message);
    }

    public NoSuficientBalanceException(String message, Throwable cause) {
        super(message, cause);
    }
}
