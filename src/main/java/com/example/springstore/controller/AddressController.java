package com.example.springstore.controller;

import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.dto.address.AddressUpdateDto;
import com.example.springstore.domain.exeption.AddressNotFoundException;
import com.example.springstore.domain.mapper.AddressMapper;
import com.example.springstore.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
 *  Controller for work with user address
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequestMapping(path = "addresses")
@RequiredArgsConstructor
@Tag(name = "Address", description = "Controller for work with user address")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Address not found")
@ApiResponse(responseCode = "401", description = "Unauthorized user")
public class AddressController {

    /** Address service injecting     */
    private final AddressService addressService;
    /** Address mapper injecting */
    private final AddressMapper addressMapper;

    /**
     * Return address on Json format
     * @param id
     * @return address on Json format
     */
    @GetMapping("/{addressId}")
    @Operation(description = "Find address by id")
    @ApiResponse(responseCode = "200", description = "Address found")
    @PreAuthorize("hasRole('ADMIN') || hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public AddressDto get(@PathVariable(name = "addressId") UUID id)
    {
        return Optional.of(id)
                .map(addressService::get)
                .map(addressMapper::toDto)
                .orElseThrow(() -> new AddressNotFoundException());
    }

    /**
     * Update address and return updated address on Json format
     * @param id
     * @param updateDto new information for update
     * @return updated address
     */
    @PatchMapping("/{addressId}")
    @Operation(description = "Update address by id")
    @ApiResponse(responseCode = "200", description = "Address updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public AddressDto update(@Valid @PathVariable(name = "addressId") UUID id, @RequestBody AddressUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(addressMapper::fromUpdateDto)
                .map(toUpdate -> addressService.update(id, toUpdate))
                .map(addressMapper::toDto)
                .orElseThrow();
    }
}
