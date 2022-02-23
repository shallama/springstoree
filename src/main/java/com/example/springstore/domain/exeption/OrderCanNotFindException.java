package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when order not found
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class OrderCanNotFindException extends RuntimeException {
    public OrderCanNotFindException(){
        super("Order not found!");
    }
}
