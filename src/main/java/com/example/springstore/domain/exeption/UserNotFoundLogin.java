package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when user was not found when user try to login
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundLogin extends RuntimeException {
    public UserNotFoundLogin() {
        super("User not found!");
    }
}
