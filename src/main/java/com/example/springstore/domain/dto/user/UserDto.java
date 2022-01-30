package com.example.springstore.domain.dto.user;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
    UUID id;
    String firstName;
    String lastName;
    String email;
    String phone;
    String role;
    UUID addressId;
}
