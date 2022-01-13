package com.example.springstore.domain.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {
    private UUID id;
    private String role;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
}
