package com.example.springstore.domain.dto.user;

import com.example.springstore.domain.entity.enums.Role;
import com.example.springstore.validation.annotation.Email;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;
import org.apache.tomcat.jni.Address;

import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "UserCreateDto", description = "Info for create user")
public class UserCreateDto {
    @NotBlank
    @Schema(description = "First name")
    String firstName;
    @NotBlank
    @Schema(description = "Last name")
    String lastName;
    @Email
    @NotBlank
    @Schema(description = "Email")
    String email;
    @Schema(description = "Phone")
    String phone;
    @Schema(description = "User role")
    Role role;
    @Schema(description = "User password")
    String password;
}
