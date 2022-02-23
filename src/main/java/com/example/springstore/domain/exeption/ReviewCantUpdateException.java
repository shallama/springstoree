package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when review can not be updated
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ReviewCantUpdateException extends RuntimeException {
    public ReviewCantUpdateException (){
        super("You can't update review, time is over!");
    }
}
