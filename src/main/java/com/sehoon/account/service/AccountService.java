package com.sehoon.account.service;

import com.sehoon.account.domain.Account;
import com.sehoon.account.domain.AccountUser;
import com.sehoon.account.dto.account.*;
import com.sehoon.account.exception.AccountException;
import com.sehoon.account.exception.UserException;
import com.sehoon.account.repository.AccountRepository;
import com.sehoon.account.repository.UserRepository;
import com.sehoon.account.type.AccountStatus;
import com.sehoon.account.util.StringGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.sehoon.account.common.ExceptionMessage.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AccountService implements AccountServiceIF {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public CreateAccountResponse createAccount(CreateAccountRequest createRequest) {
        Optional<AccountUser> user = userRepository.findById(createRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserException(NOT_FOUND_USER.getMessage());
        }

        int accountCounts = accountRepository.findAccountsByAccountUserId_Id(createRequest.getUserId()).size();
        if (10 == accountCounts) {
            throw new AccountException(ACCOUNT_CREATE_LIMIT.getMessage());
        }

        String accountNum = StringGenerator.generateAccountNumber();
        Account alreadyUsedNumber = accountRepository.findAccountByAccountNumber(accountNum);

        while (alreadyUsedNumber != null) {
            accountNum = StringGenerator.generateAccountNumber();
            alreadyUsedNumber = accountRepository.findAccountByAccountNumber(accountNum);
        }

        Account account = accountRepository.save(createRequest.toEntity(user.get()));
        return new CreateAccountResponse(account);
    }

    @Override
    public UnregisterAccountResponse unregisterAccount(UnregisterAccountRequest unregisterRequest) {
        Optional<AccountUser> user = userRepository.findById(unregisterRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserException(NOT_FOUND_USER.getMessage());
        }

        Account account = findAccount(unregisterRequest.getAccountNumber());
        if (account == null) {
            throw new AccountException(NOT_FOUND_ACCOUNT.getMessage());
        }

        if (!account.getAccountUserId().getId()
                .equals(unregisterRequest.getUserId())) {
            throw new AccountException(NO_MATCH_USER_ACCOUNT.getMessage());
        }

        if (account.getAccountStatus() == AccountStatus.UNREGISTERED) {
            throw new AccountException(ALREADY_UNREGISTERED.getMessage());
        }

        if (account.getBalance() > 0) {
            throw new AccountException(EXIST_BALANCE.getMessage());
        }

        account.unregister();
        return new UnregisterAccountResponse(account);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AccountItem> findAllAccounts(Long userId) {
        Optional<AccountUser> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserException(NOT_FOUND_USER.getMessage());
        }

        return accountRepository.findAccountsByAccountUserId_Id(userId)
                .stream()
                .map(AccountItem::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Account findAccount(String accountNumber) {
        return accountRepository.findAccountByAccountNumber(accountNumber);
    }
}
