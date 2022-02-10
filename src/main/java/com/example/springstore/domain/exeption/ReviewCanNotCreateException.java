package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ReviewCanNotCreateException extends RuntimeException {
    public ReviewCanNotCreateException(){
        super("Item hasn't been updated!");
    }
}
