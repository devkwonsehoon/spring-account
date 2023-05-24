package com.sehoon.account.service;

import com.sehoon.account.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public boolean existUser(Long userId) {
        return userRepository.findById(userId).isPresent();
    }
}
