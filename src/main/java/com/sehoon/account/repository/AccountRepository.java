package com.sehoon.account.repository;

import com.sehoon.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByAccountNumber(String accountNumber);

    List<Account> findAccountsByAccountUserId_Id(Long userId);
}
