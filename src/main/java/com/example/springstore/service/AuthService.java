package com.example.springstore.service;

import com.example.springstore.domain.dto.security.LoginRequest;
import com.example.springstore.domain.dto.user.UserCreateDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String login(LoginRequest loginRequest);

    String signUp(UserCreateDto createDto);
}
