package com.example.springstore.domain.dto.user;

import com.example.springstore.validation.annotation.Email;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "UserUpdateDto", description = "Info for update user")
public class UserUpdateDto {
    @Schema(description = "First name")
    String firstName;
    @Schema(description = "Last name")
    String lastName;
    @Email
    @Schema(description = "Email")
    String email;
    @Schema(description = "Phone")
    String phone;
}
