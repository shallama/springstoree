package com.example.springstore.controller;

import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.dto.address.AddressUpdateDto;
import com.example.springstore.domain.exeption.AddressNotFoundException;
import com.example.springstore.domain.mapper.AddressMapper;
import com.example.springstore.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "addresses")
@RequiredArgsConstructor
public class AddressController {
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final AddressMapper addressMapper;

    @SneakyThrows
    @GetMapping("/{addressId}")
    public AddressDto get(@PathVariable(name = "addressId") UUID id)
    {
        return Optional.of(id)
                .map(addressService::get)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new AddressNotFoundException(id));
    }

    @SneakyThrows
    @PatchMapping("/{addressId}")
    @ResponseStatus(value = OK)
    public AddressDto update(@Valid @PathVariable(name = "addressId") UUID id, @RequestBody AddressUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(addressMapper::fromUpdateDto)
                .map(toUpdate -> addressService.update(id, toUpdate))
                .map(addressMapper::toDto)
                .orElseThrow();
    }
}
