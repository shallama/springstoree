package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when item was not saved
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_MODIFIED)
public class ItemWasNotSavedException extends RuntimeException{
    public ItemWasNotSavedException(){
        super("Item was not saved!");
    }
}
