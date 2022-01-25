package com.example.springstore.domain.exeption;

import com.example.springstore.domain.entity.Address;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException (UUID id) {
        super("Address not found: id =" + id);
    }
}
