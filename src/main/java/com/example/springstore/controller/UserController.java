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
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final UserService userService;
    @Autowired
    private final AddressMapper addressMapper;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final ReviewMapper reviewMapper;
    /**
     * Return user on JSON format
     *
     * @param id user id
     * @return user on JSON format
     */

    @SneakyThrows
    @GetMapping("/{userId}")
    public UserDto get(@PathVariable(name = "userId") UUID id) {
        return Optional.of(id)
                .map(userService::get)
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @SneakyThrows
    @GetMapping("/info/{userId}")
    public UserInfoDto getInfo(@PathVariable(name = "userId") UUID id) {
        return Optional.ofNullable(id)
                .map(userService::get)
                .map(userMapper::toInfoDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @PostMapping
    @ResponseStatus(value = OK)
    public UserDto create(@RequestBody UserCreateDto createDto) {
        return Optional.ofNullable(createDto)
                .map(userMapper::fromCreateDto)
                .map(userService::create)
                .map(userMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{userId}")
    @ResponseStatus(value = OK)
    public UserDto update(@PathVariable(name = "userId") UUID id, @RequestBody UserUpdateDto updateDto) {
        return Optional.ofNullable(updateDto)
                .map(userMapper::fromUpdateDto)
                .map(toUpdate -> userService.update(id, toUpdate))
                .map(userMapper::toDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable(name = "userId") UUID id) {
        userService.delete(id);
    }


    @GetMapping("/{userId}/addresses/{addressId}")
    public AddressDto getAddress(@PathVariable UUID userId, @PathVariable UUID addressId) {
        return Optional.of(addressId)
                .map(addressService::get)
                .map(addressMapper::toDto)
                .orElseThrow();
    }

    @PostMapping("/{userId}/addresses")
    @ResponseStatus(value = OK)
    public AddressDto assignAddress(@PathVariable UUID userId, @RequestBody AddressCreateDto createDto) {

        return Optional.ofNullable(createDto)
                .map(addressMapper::fromCreateDto)
                .map(toSave -> userService.assignAddress(userId, toSave))
                .map(addressMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public User deleteAddress(@PathVariable UUID userId, @PathVariable UUID addressId) {
        return userService.deleteAddress(userId, addressId);
    }

    @PatchMapping("/{userId}/addresses/{addressId}")
    @ResponseStatus(value = OK)
    public AddressDto updateAddress(@PathVariable UUID userId, @PathVariable UUID addressId, @RequestBody AddressUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(addressMapper::fromUpdateDto)
                .map(current -> userService.updateAddress(userId, addressId, current))
                .map(addressMapper::toDto)
                .orElseThrow();
    }

    @SneakyThrows
    @GetMapping("/{userId}/reviews")
    public List<ReviewDto> getReviewsByUser(@PathVariable(name = "userId") UUID userId){
        return Optional.of(userId)
                .map(reviewService::getReviewsByUser)
                .map(reviewMapper::listToDto)
                .orElseThrow();
    }

    /*@PatchMapping("/redirect/{userId}")
    public ModelAndView redirect(@PathVariable(name = "userId") UUID id, @RequestBody UserUpdateDto updateDto) {
        return new ModelAndView("redirect:/users/{userId}");
    }

    @PatchMapping("/forward/{userId}")
    public ModelAndView forward() {
        return new ModelAndView("forward:/users/{userId}");
    }*/

}
