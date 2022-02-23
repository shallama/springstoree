package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when user can not be signed up
 *  because already there are user with this email
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotLoginException extends RuntimeException {
    public UserNotLoginException(){
        super("There are user with same email in the system!");
    }
}
