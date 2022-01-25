package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException{
    public ReviewNotFoundException(UUID id){
        super("Review not found: id =" + id);}
}
