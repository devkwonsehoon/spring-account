package com.sehoon.account.dto.transcation;

import com.sehoon.account.domain.Account;
import com.sehoon.account.domain.Transaction;
import com.sehoon.account.type.TransactionResultType;
import com.sehoon.account.type.TransactionType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import static com.sehoon.account.util.StringGenerator.generateTransactionNumber;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CancelUseMoneyRequest {
    String transactionId;
    String accountNumber;
    Long amount;

    public Transaction toEntity(Account account, TransactionResultType transactionResultType) {
        return Transaction.builder()
                .transactionType(TransactionType.CANCEL)
                .transactionResultType(transactionResultType)
                .accountId(account)
                .amount(this.amount)
                .balanceSnapshot(account.getBalance() - this.amount)
                .transactionId(generateTransactionNumber())
                .build();
    }
}