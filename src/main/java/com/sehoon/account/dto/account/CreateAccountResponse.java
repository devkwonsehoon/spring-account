package com.sehoon.account.dto.account;

import com.sehoon.account.domain.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountResponse {
    Long userId;
    String accountNumber;
    LocalDateTime registeredAt;

    public CreateAccountResponse(Account account) {
        this.userId = account.getAccountUserId().getId();
        this.accountNumber = account.getAccountNumber();
        this.registeredAt = account.getRegisteredAt();
    }
}
