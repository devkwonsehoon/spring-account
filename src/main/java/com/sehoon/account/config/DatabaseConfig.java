package com.sehoon.account.config;

import com.sehoon.account.domain.AccountUser;
import com.sehoon.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

    private final UserRepository userRepository;

    @Bean
    public CommandLineRunner initializeDatabase() {
        return args -> {
            AccountUser accountUser = new AccountUser(1L);
            userRepository.save(accountUser);
        };
    }
}
