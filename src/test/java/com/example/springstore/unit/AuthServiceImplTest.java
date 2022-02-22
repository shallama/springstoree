package com.example.springstore.unit;

import com.example.springstore.domain.dto.security.LoginRequest;
import com.example.springstore.domain.entity.User;
import com.example.springstore.repository.UserRepository;
import com.example.springstore.service.TokenService;
import com.example.springstore.service.UserService;
import com.example.springstore.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.same;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {
    @InjectMocks
    private AuthServiceImpl authServiceImpl;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private UserService userService;
    @Mock
    private TokenService tokenService;
    @Mock
    private UserRepository userRepository;

    @Test
    public void testUserNotFoundByEmail() {
        String username = "username";
        LoginRequest loginRequest = LoginRequest.builder().username(username).build();
        Mockito.when(userRepository.findByEmail(username)).thenReturn(Optional.empty());
        try {
            authServiceImpl.login(loginRequest);
            fail("");
        } catch (RuntimeException e) {
            Assertions.assertEquals("User not found!", e.getMessage());
        }
    }

    @Test
    public void testMatchPasswordTrue() {
        String username = "username";
        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password("password1")
                .build();
        User user = new User();
        user.setPassword("password");
        String exceptedToken = "exceptedToken";
        Mockito.when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(true);
        Mockito.when(tokenService.generateToken(user)).thenReturn(exceptedToken);
        String result = authServiceImpl.login(loginRequest);
        Mockito.verify(tokenService, Mockito.times(1)).generateToken(same(user));
        Assertions.assertEquals(exceptedToken, result);
    }

    @Test
    public void testMatchPasswordFalse() {
        String username = "username";
        LoginRequest loginRequest = LoginRequest.builder()
                .username(username)
                .password("password1")
                .build();
        User user = new User();
        user.setPassword("password");
        Mockito.when(userRepository.findByEmail(username)).thenReturn(Optional.of(user));
        Mockito.when(passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())).thenReturn(false);
        Assertions.assertThrows(RuntimeException.class, () -> authServiceImpl.login(loginRequest));
        Mockito.verify(tokenService, Mockito.times(0)).generateToken(same(user));
    }
}
