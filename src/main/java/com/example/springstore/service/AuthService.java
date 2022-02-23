package com.example.springstore.service;

import com.example.springstore.domain.dto.security.LoginRequest;
import com.example.springstore.domain.dto.user.UserCreateDto;
import org.springframework.stereotype.Service;
/**
 *  Service for Authentication and registration user
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface AuthService {
    /**
     * Login user to system and return token
     * @param loginRequest dto from login page
     * @return token
     */
    String login(LoginRequest loginRequest);

    /**
     * Sign up new user with unique username (email) and return token
     * @param createDto dto which contain all arguments for registration user
     * @return token
     */
    String signUp(UserCreateDto createDto);
}
