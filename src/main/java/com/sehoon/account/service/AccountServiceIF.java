package com.sehoon.account.service;

import com.sehoon.account.dto.account.*;

import java.util.List;

public interface AccountServiceIF {
    CreateAccountResponse createAccount(CreateAccountRequest createRequest) throws IllegalAccessException;

    UnregisterAccountResponse unregisterAccount(UnregisterAccountRequest unregisterRequest);

    List<AccountItem> findAllAccounts(Long userId);
}
