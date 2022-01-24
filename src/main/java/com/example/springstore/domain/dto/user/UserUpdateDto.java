package com.example.springstore.domain.dto.user;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Integer version;
}
