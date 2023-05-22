package com.sehoon.account.exception;

public class AccountException extends RuntimeException {
    public AccountException(String exceptionMessage) {
        super("[Account Exception] - " + exceptionMessage);
    }
}
