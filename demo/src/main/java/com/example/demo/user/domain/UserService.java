package com.example.demo.user.domain;

import com.example.demo.user.infrastructure.BaseUserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final BaseUserRepository<User> userRepository;

    public UserService(BaseUserRepository<User> userRepository) {
        this.userRepository = userRepository;
    }
}