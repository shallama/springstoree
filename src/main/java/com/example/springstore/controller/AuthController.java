package com.example.springstore.controller;

import com.example.springstore.domain.dto.security.LoginRequest;
import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
/**
 *  Controller for work with authentication
 *  @author tagir
 *  @since 20.02.2022
 */
@RestController
@RequestMapping(path = "auth")
@RequiredArgsConstructor
@Tag(name = "Item", description = "Controller for work with authentication")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "User not found")
public class AuthController {
    /** Authentications service injecting*/
    private final AuthService authService;

    /**
     * Validate user information to getting a token
     * and gain access to other controllers
     * @param loginRequest
     * @return token
     */
    @PostMapping("login")
    @Operation(description = "Login user by email and password")
    @ApiResponse(responseCode = "200", description = "Item login")
    public String login(@RequestBody LoginRequest loginRequest) {
        return Optional.ofNullable(loginRequest)
                .map(authService::login)
                .orElseThrow();
    }

    /**
     * Sign up new user and return token
     * @param userCreateDto
     * @return token
     */
    @PostMapping("sign-up")
    @Operation(description = "Sign up user")
    @ApiResponse(responseCode = "200", description = "Item signed up")
    public String signUp(@RequestBody UserCreateDto userCreateDto) {
        return Optional.ofNullable(userCreateDto)
                .map(authService::signUp)
                .orElseThrow();
    }
}
