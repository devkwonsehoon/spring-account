package com.sehoon.account.dto.transcation;

import com.sehoon.account.domain.Transaction;
import com.sehoon.account.type.TransactionResultType;
import com.sehoon.account.type.TransactionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDetailResponse {
    String accountNumber;
    TransactionType transactionType;
    TransactionResultType transactionResult;
    String transactionId;
    Long amount;
    LocalDateTime transactedAt;

    public TransactionDetailResponse(Transaction transaction) {
        this.accountNumber = transaction.getAccountId().getAccountNumber();
        this.transactionType = transaction.getTransactionType();
        this.transactionResult = transaction.getTransactionResultType();
        this.transactionId = transaction.getTransactionId();
        this.amount = transaction.getAmount();
        this.transactedAt = transaction.getTransactedAt();
    }
}
