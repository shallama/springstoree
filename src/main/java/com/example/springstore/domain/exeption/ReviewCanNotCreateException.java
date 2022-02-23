package com.example.springstore.domain.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 *  Exception when review can not be created
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class ReviewCanNotCreateException extends RuntimeException {
    public ReviewCanNotCreateException(){
        super("Item hasn't been updated!");
    }
}
