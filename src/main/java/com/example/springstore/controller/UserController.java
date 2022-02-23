package com.example.springstore.controller;

import com.example.springstore.domain.dto.address.AddressCreateDto;
import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.dto.address.AddressUpdateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.domain.dto.user.UserDto;
import com.example.springstore.domain.dto.user.UserInfoDto;
import com.example.springstore.domain.dto.user.UserUpdateDto;
import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.exeption.UserNotFoundException;
import com.example.springstore.domain.mapper.AddressMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.domain.mapper.UserMapper;
import com.example.springstore.service.AddressService;
import com.example.springstore.service.ReviewService;
import com.example.springstore.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
/**
 *  Controller for work with user information
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
@Tag(name = "User", description = "Controller for work with user")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "User not found")
@ApiResponse(responseCode = "401", description = "Unauthorized user")
public class UserController {

    /** User mapper injecting     */
    private final UserMapper userMapper;
    /** User service injecting     */
    private final UserService userService;
    /** Address mapper injecting     */
    private final AddressMapper addressMapper;

    /**
     * Return user on JSON format
     * @param id user id
     * @return user on JSON format
     */
    @GetMapping("/{userId}")
    @Operation(description = "Find user by id")
    @ApiResponse(responseCode = "200", description = "User found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public UserDto get(@PathVariable(name = "userId") UUID id) {
        return Optional.of(id)
                .map(userService::get)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Return user with detailed information by id on Json format
     * @param id
     * @return user on Json format
     */
    @GetMapping("/info/{userId}")
    @Operation(description = "Find user info by id")
    @ApiResponse(responseCode = "200", description = "User info found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public UserInfoDto getInfo(@PathVariable(name = "userId") UUID id) {
        return Optional.ofNullable(id)
                .map(userService::get)
                .map(userMapper::toInfoDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Update user and return updated user
     * @param id
     * @param updateDto
     * @return updated user on Json format
     */
    @PatchMapping("/{userId}")
    @Operation(description = "Update user by id")
    @ApiResponse(responseCode = "200", description = "User updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public UserDto update(@PathVariable(name = "userId") UUID id, @RequestBody UserUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(userMapper::fromUpdateDto)
                .map(toUpdate -> userService.update(id, toUpdate))
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Delete user by id
     * @param id
     */
    @DeleteMapping("/{userId}")
    @Operation(description = "Delete user and his address by id")
    @ApiResponse(responseCode = "204", description = "User deleted")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public void delete(@PathVariable(name = "userId") UUID id) {
        userService.delete(id);
    }

    /**
     * Create address for user
     * @param userId
     * @param createDto
     * @return created address on Json format
     */
    @PostMapping("/{userId}/addresses")
    @Operation(description = "Create address")
    @ApiResponse(responseCode = "200", description = "Address created")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public AddressDto createAddress(@PathVariable UUID userId, @RequestBody AddressCreateDto createDto) {

        return Optional.ofNullable(createDto)
                .map(addressMapper::fromCreateDto)
                .map(toSave -> userService.createAddress(userId, toSave))
                .map(addressMapper::toDto)
                .orElseThrow();
    }

    /**
     * Update user address
     * @param userId
     * @param updateDto
     * @return updated user address
     */
    @PatchMapping("/{userId}/addresses/")
    @Operation(description = "Update address by id")
    @ApiResponse(responseCode = "200", description = "Address Updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public AddressDto updateAddress(@PathVariable UUID userId, @RequestBody AddressUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(addressMapper::fromUpdateDto)
                .map(current -> userService.updateAddress(userId, current))
                .map(addressMapper::toDto)
                .orElseThrow();
    }

}
