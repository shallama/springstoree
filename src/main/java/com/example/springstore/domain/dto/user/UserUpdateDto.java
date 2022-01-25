package com.example.springstore.domain.dto.user;

import com.example.springstore.validation.annotation.Email;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateDto {
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phone;
}
