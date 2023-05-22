package com.sehoon.account.controller;

import com.sehoon.account.dto.transcation.CancelUseMoneyRequest;
import com.sehoon.account.dto.transcation.TransactionDetailResponse;
import com.sehoon.account.dto.transcation.TransactionResponse;
import com.sehoon.account.dto.transcation.UseMoneyRequest;
import com.sehoon.account.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/use")
    public ResponseEntity<TransactionResponse> use(@RequestBody UseMoneyRequest useMoneyRequest) {
        TransactionResponse response = transactionService.use(useMoneyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/cancel")
    public ResponseEntity<TransactionResponse> cancelUse(@RequestBody CancelUseMoneyRequest cancelUseMoneyRequest) {
        TransactionResponse response = transactionService.cancelUse(cancelUseMoneyRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionDetailResponse> getTransaction(@PathVariable String transactionId) {
        TransactionDetailResponse response = transactionService.getTransaction(transactionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
