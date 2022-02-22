package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotLoginException extends RuntimeException {
    public UserNotLoginException(){
        super("There are user with same email in the system!");
    }
}
