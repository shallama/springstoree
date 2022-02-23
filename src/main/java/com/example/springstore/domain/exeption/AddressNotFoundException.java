package com.example.springstore.domain.exeption;

import com.example.springstore.domain.entity.Address;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
/**
 *  Exception when address not found
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException () {
        super("Address not found!");
    }
}
