package com.example.springstore.domain.exeption;

import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;
/**
 *  Exception when item group not found
 *  @author tagir
 *  @since 15.01.2022
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ItemGroupNotFoundException extends RuntimeException {
    public ItemGroupNotFoundException (UUID id){
        super("Group not found: id =" + id);
    }
}
