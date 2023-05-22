package com.sehoon.account.exception;

public class TransactionException extends RuntimeException {
    public TransactionException(String exceptionMessage) {
        super("[Transaction Exception] - " + exceptionMessage);
    }
}
