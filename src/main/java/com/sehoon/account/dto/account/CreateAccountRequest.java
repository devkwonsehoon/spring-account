package com.sehoon.account.dto.account;

import com.sehoon.account.domain.Account;
import com.sehoon.account.domain.AccountUser;
import com.sehoon.account.type.AccountStatus;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.sehoon.account.util.StringGenerator.generateAccountNumber;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateAccountRequest {
    Long userId;
    Long initBalance;

    public Account toEntity(AccountUser user) {
        return Account.builder()
                .accountUserId(user)
                .accountNumber(generateAccountNumber())
                .accountStatus(AccountStatus.IN_USE)
                .balance(this.initBalance)
                .build();
    }
}
