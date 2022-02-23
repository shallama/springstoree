package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when order can not be deleted
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class OrderCantDeleteException extends RuntimeException{
    public OrderCantDeleteException(){
        super("It is not possible to delete an order because it has already been sent");
    }
}
