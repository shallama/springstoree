package com.example.springstore.domain.dto.user;

import com.example.springstore.validation.annotation.Email;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateDto {
    String firstName;
    String lastName;
    @Email
    String email;
    String phone;
    String password;
}
