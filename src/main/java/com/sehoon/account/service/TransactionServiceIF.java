package com.sehoon.account.service;

import com.sehoon.account.dto.transcation.CancelUseMoneyRequest;
import com.sehoon.account.dto.transcation.TransactionDetailResponse;
import com.sehoon.account.dto.transcation.TransactionResponse;
import com.sehoon.account.dto.transcation.UseMoneyRequest;

public interface TransactionServiceIF {
    TransactionResponse use(UseMoneyRequest useMoneyRequest);

    TransactionResponse cancelUse(CancelUseMoneyRequest cancelUseMoneyRequest);

    TransactionDetailResponse getTransaction(String transactionId);
}
