package com.example.springstore.domain.dto.user;

import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.apache.tomcat.jni.Address;

import javax.persistence.OneToOne;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserCreateDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
}
