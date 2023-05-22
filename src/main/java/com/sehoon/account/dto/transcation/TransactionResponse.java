package com.sehoon.account.dto.transcation;

import com.sehoon.account.domain.Transaction;
import com.sehoon.account.type.TransactionResultType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    String accountNumber;
    TransactionResultType transactionResult;
    String transactionId;
    Long amount;
    LocalDateTime transactedAt;

    public TransactionResponse(Transaction transaction) {
        this.accountNumber = transaction.getAccountId().getAccountNumber();
        this.transactionResult = transaction.getTransactionResultType();
        this.transactionId = transaction.getTransactionId();
        this.amount = transaction.getAmount();
        this.transactedAt = transaction.getTransactedAt();
    }
}
