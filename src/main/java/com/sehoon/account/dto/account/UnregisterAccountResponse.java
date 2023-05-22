package com.sehoon.account.dto.account;

import com.sehoon.account.domain.Account;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UnregisterAccountResponse {
    Long userId;
    String accountNumber;
    LocalDateTime unRegisteredAt;

    public UnregisterAccountResponse(Account account) {
        this.userId = account.getAccountUserId().getId();
        this.accountNumber = account.getAccountNumber();
        this.unRegisteredAt = account.getUnregisteredAt();
    }
}
