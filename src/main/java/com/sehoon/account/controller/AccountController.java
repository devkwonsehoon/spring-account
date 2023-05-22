package com.sehoon.account.controller;

import com.sehoon.account.dto.account.*;
import com.sehoon.account.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @PostMapping("")
    public ResponseEntity<CreateAccountResponse> createAccount(@RequestBody CreateAccountRequest createRequest) {
        CreateAccountResponse response = accountService.createAccount(createRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @DeleteMapping("")
    public ResponseEntity<UnregisterAccountResponse> unregisterAccount(@RequestBody UnregisterAccountRequest unregisterRequest) {
        UnregisterAccountResponse response = accountService.unregisterAccount(unregisterRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("")
    public ResponseEntity<List<AccountItem>> getAccounts(@RequestParam("user_id") Long userId) {
        List<AccountItem> response = accountService.findAllAccounts(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
