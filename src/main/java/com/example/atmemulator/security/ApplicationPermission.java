package com.example.atmemulator.security;

public enum ApplicationPermission {
    TRANSACTION_DEPOSIT("transaction:deposit"),
    TRANSACTION_WITHDRAWAL("transaction:withdrawal"),
    TRANSACTION_BALANCE_CHECK("transaction:balanceCheck");

    private final String permission;

    ApplicationPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}

