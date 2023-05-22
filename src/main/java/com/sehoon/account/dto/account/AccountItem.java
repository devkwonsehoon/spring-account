package com.sehoon.account.dto.account;

import com.sehoon.account.domain.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AccountItem {
    String accountNumber;
    Long balance;

    public AccountItem(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.balance = account.getBalance();
    }
}
