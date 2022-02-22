package com.example.springstore.service;

import com.example.springstore.domain.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface TokenService {
    String generateToken(User user);

    String extractUsernameAndValidate(String token);
}
