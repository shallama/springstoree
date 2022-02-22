package com.example.springstore.domain.dto.user;

import com.example.springstore.domain.entity.enums.Role;
import com.example.springstore.validation.annotation.Email;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.apache.tomcat.jni.Address;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateDto {
    @NotBlank
    String firstName;
    @NotBlank
    String lastName;
    @Email
    @NotBlank
    String email;
    String phone;
    Role role;
    String password;
}
