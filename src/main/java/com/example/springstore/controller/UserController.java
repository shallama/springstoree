package com.example.springstore.controller;

import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.domain.dto.user.UserDto;
import com.example.springstore.domain.dto.user.UserInfoDto;
import com.example.springstore.domain.dto.user.UserUpdateDto;
import com.example.springstore.domain.exeption.UserNotFoundException;
import com.example.springstore.domain.mapper.UserMapper;
import com.example.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "users")
@RequiredArgsConstructor
public class UserController {
    private UserMapper userMapper;
    private UserService userService;

    /**
     * Return user on JSON format
     *
     * @param id user id
     * @return user on JSON format
     */
    //path = http://localhost:8080/api/v1.0/users
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

   /* @GetMapping("/{userId}/addresses/{addressId}")
    public AddressDto getAddress(@PathVariable UUID userId, @PathVariable UUID addressId) {
        return null;
    }

    @PostMapping("/{userId}/addresses")
    public AddressDto assignAddress(@PathVariable UUID userId, @RequestBody AddressCreateDto createDto) {
//        final Address address = mapper.toDto(createDto);
//        return service.assignAddress(userId, address);
        return null;
    }

    @PatchMapping("/{userId}/addresses/{addressId}")
    public AddressDto updateAddress(@PathVariable UUID userId, @PathVariable UUID addressId, @RequestBody AddressUpdateDto createDto) {
        return null;
    }

    @DeleteMapping("/{userId}/addresses/{addressId}")
    public AddressDto deleteAddress(@PathVariable UUID userId, @PathVariable UUID addressId) {
        return null;
    }*/

    /*@PatchMapping("/redirect/{userId}")
    public ModelAndView redirect(@PathVariable(name = "userId") UUID id, @RequestBody UserUpdateDto updateDto) {
        return new ModelAndView("redirect:/users/{userId}");
    }

    @PatchMapping("/forward/{userId}")
    public ModelAndView forward() {
        return new ModelAndView("forward:/users/{userId}");
    }
*/
}
