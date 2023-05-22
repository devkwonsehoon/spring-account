package com.sehoon.account.exception;

public class UserException extends RuntimeException {
    public UserException(String exceptionMessage) {
        super("[User Exception] - " + exceptionMessage);
    }
}
