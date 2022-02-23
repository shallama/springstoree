package com.example.springstore.controller;

import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.dto.address.AddressUpdateDto;
import com.example.springstore.domain.exeption.AddressNotFoundException;
import com.example.springstore.domain.mapper.AddressMapper;
import com.example.springstore.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

/**
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequestMapping(path = "addresses")
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;
    private final AddressMapper addressMapper;

    @GetMapping("/{addressId}")
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public AddressDto get(@PathVariable(name = "addressId") UUID id)
    {
        return Optional.of(id)
                .map(addressService::get)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new AddressNotFoundException());
    }

    @PatchMapping("/{addressId}")
    @ResponseStatus(value = OK)
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public AddressDto update(@Valid @PathVariable(name = "addressId") UUID id, @RequestBody AddressUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(addressMapper::fromUpdateDto)
                .map(toUpdate -> addressService.update(id, toUpdate))
                .map(addressMapper::toDto)
                .orElseThrow();
    }
}
