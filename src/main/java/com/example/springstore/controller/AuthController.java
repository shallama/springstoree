package com.example.springstore.controller;

import com.example.springstore.domain.dto.security.LoginRequest;
import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
/**
 *  @author tagir
 *  @since 20.02.2022
 */
@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return Optional.ofNullable(loginRequest)
                .map(authService::login)
                .orElseThrow();
    }

    @PostMapping("sign-up")
    public String signUp(@RequestBody UserCreateDto userCreateDto) {
        return Optional.ofNullable(userCreateDto)
                .map(authService::signUp)
                .orElseThrow();
    }
}
