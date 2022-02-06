package com.example.atmemulator.exceptions;

public class CreditCardException extends RuntimeException {
    public CreditCardException() {
    }

    public CreditCardException(String message) {
        super(message);
    }

    public CreditCardException(String message, Throwable cause) {
        super(message, cause);
    }
}
