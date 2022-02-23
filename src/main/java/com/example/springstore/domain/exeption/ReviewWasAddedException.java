package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  Exception when review was added for order
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ReviewWasAddedException extends RuntimeException {
    public ReviewWasAddedException(){
        super("Review was added for this order!");
    }
}
