package com.sehoon.account.service;

import com.sehoon.account.domain.Account;
import com.sehoon.account.domain.AccountUser;
import com.sehoon.account.domain.Transaction;
import com.sehoon.account.dto.transcation.CancelUseMoneyRequest;
import com.sehoon.account.dto.transcation.TransactionDetailResponse;
import com.sehoon.account.dto.transcation.TransactionResponse;
import com.sehoon.account.dto.transcation.UseMoneyRequest;
import com.sehoon.account.exception.AccountException;
import com.sehoon.account.exception.TransactionException;
import com.sehoon.account.exception.UserException;
import com.sehoon.account.repository.AccountRepository;
import com.sehoon.account.repository.TransactionRepository;
import com.sehoon.account.repository.UserRepository;
import com.sehoon.account.type.AccountStatus;
import com.sehoon.account.type.TransactionResultType;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import static com.sehoon.account.common.ExceptionMessage.*;

@RequiredArgsConstructor
@Service
public class TransactionService implements TransactionServiceIF {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final RedissonClient redissonClient;

    @Override
    public TransactionResponse use(UseMoneyRequest useMoneyRequest) {
        Optional<AccountUser> user = userRepository.findById(useMoneyRequest.getUserId());
        if (user.isEmpty()) {
            throw new UserException(NOT_FOUND_USER.getMessage());
        }

        Account account = accountRepository.findAccountByAccountNumber(useMoneyRequest.getAccountNumber());
        if (!account.getAccountUserId().getId()
                .equals(useMoneyRequest.getUserId())) {
            throw new AccountException(NO_MATCH_USER_ACCOUNT.getMessage());
        }

        if (account.getAccountStatus() == AccountStatus.UNREGISTERED) {
            throw new AccountException(ALREADY_UNREGISTERED.getMessage());
        }

        if (account.getBalance() < useMoneyRequest.getAmount()) {
            throw new TransactionException(OVER_BALANCE.getMessage());
        }

        if (useMoneyRequest.getAmount() > Long.MAX_VALUE - 10 || useMoneyRequest.getAmount() < 0) {
            throw new TransactionException(UNSUPPORTED_AMOUNT.getMessage());
        }

        Transaction transaction;
        RLock lock = redissonClient.getLock("useTransactionLock");
        try {
            lock.lock();
            transaction = transactionRepository.save(useMoneyRequest.toEntity(account, TransactionResultType.SUCCESS));
        } finally {
            lock.unlock();
        }
        return new TransactionResponse(transaction);
    }

    @Override
    public TransactionResponse cancelUse(CancelUseMoneyRequest cancelUseMoneyRequest) {
        Transaction transaction = transactionRepository.findTransactionByTransactionId(cancelUseMoneyRequest.getTransactionId());
        if (transaction == null) {
            throw new TransactionException(NOT_FOUND_TRANSACTION.getMessage());
        }

        Account account = accountRepository.findAccountByAccountNumber(cancelUseMoneyRequest.getAccountNumber());
        if (account == null) {
            throw new AccountException(NOT_FOUND_ACCOUNT.getMessage());
        }

        if (!account.getAccountNumber().equals(cancelUseMoneyRequest.getAccountNumber())) {
            throw new TransactionException(NO_MATCH_TRANSACTION_ACCOUNT.getMessage());
        }

        if (!transaction.getAmount().equals(cancelUseMoneyRequest.getAmount())) {
            throw new TransactionException(NO_MATCH_CANCEL_AMOUNT.getMessage());
        }

        int today = LocalDateTime.now().getYear();
        if (today - transaction.getTransactedAt().getYear() > 0) {
            throw new TransactionException(OVER_YEAR.getMessage());
        }

        Transaction newTransaction;
        RLock lock = redissonClient.getLock("useTransactionLock");
        try {
            lock.lock();
            newTransaction = transactionRepository.save(cancelUseMoneyRequest.toEntity(account, TransactionResultType.SUCCESS));
        } finally {
            lock.unlock();
        }
        return new TransactionResponse(newTransaction);
    }

    @Override
    public TransactionDetailResponse getTransaction(String transactionId) {
        Transaction transaction = transactionRepository.findTransactionByTransactionId(transactionId);
        if (transaction == null) {
            throw new TransactionException(NOT_FOUND_TRANSACTION.getMessage());
        }

        return new TransactionDetailResponse(transaction);
    }
}
