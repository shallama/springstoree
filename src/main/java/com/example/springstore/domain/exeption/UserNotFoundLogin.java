package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundLogin extends RuntimeException {
    public UserNotFoundLogin() {
        super("User not found!");
    }
}
