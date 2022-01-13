package com.example.springstore.domain.dto.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoDto {
    private String firstName;
    private String lastName;
    private String role;
    private String email;
    private String phone;
}
