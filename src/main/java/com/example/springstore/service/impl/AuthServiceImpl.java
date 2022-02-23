package com.example.springstore.service.impl;

import com.example.springstore.domain.dto.security.LoginRequest;
import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.exeption.IncorrectPasswordException;
import com.example.springstore.domain.exeption.UserNotFoundLogin;
import com.example.springstore.domain.exeption.UserNotLoginException;
import com.example.springstore.repository.UserRepository;
import com.example.springstore.service.AuthService;
import com.example.springstore.service.TokenService;
import com.example.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.LifecycleState;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Authentication service implementation
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final TokenService tokenService;
    private final UserRepository userRepository;

    @Override
    public String login(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getUsername())
                .orElseThrow(() -> new UserNotFoundLogin());
        if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return tokenService.generateToken(user);
        } else {
            throw new IncorrectPasswordException();
        }
    }

    @Override
    public String signUp(UserCreateDto userCreateDto) {
        if (userRepository.existsByEmail(userCreateDto.getEmail())){
            throw new UserNotLoginException();
        }
        User user = new User();
        user.setFirstName(userCreateDto.getFirstName());
        user.setLastName(userCreateDto.getLastName());
        user.setPhone(userCreateDto.getPhone());
        user.setRole(userCreateDto.getRole());
        user.setEmail(userCreateDto.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));
        userService.create(user);
        return tokenService.generateToken(user);
    }
}
