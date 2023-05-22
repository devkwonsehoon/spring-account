package com.sehoon.account.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionMessage {

    // User exception messages
    NOT_FOUND_USER("존재하지 않는 회원입니다."),

    // Account exception messages
    NOT_FOUND_ACCOUNT("존재하지 않는 계좌입니다."),
    ACCOUNT_CREATE_LIMIT("최대 보유 가능 계좌 수를 초과했습니다."),
    NO_MATCH_USER_ACCOUNT("해당 유저와 계좌 소유주가 일치하지 않습니다."),
    ALREADY_UNREGISTERED("이미 해지된 계좌입니다."),
    EXIST_BALANCE("계좌 내 잔액이 존재합니다."),

    // Transaction exception messages
    NOT_FOUND_TRANSACTION("존재하지 않는 거래입니다."),
    NO_MATCH_TRANSACTION_ACCOUNT("해당 거래와 계좌가 일치하지 않습니다."),
    NO_MATCH_CANCEL_AMOUNT("거래 금액과 취소 금액이 일치하지 않습니다."),
    OVER_BALANCE("계좌 내 잔액이 부족합니다."),
    UNSUPPORTED_AMOUNT("거래 금액이 너무 작거나 큽니다."),
    OVER_YEAR("1년이 넘은 거래는 취소할 수 없습니다.");

    private final String message;
}
