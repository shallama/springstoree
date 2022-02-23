package com.example.springstore.domain.dto.user;

import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.enums.Role;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "UserInfo", description = "Detailed Info about user")
public class UserInfoDto {
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
    @Schema(description = "First name")
    String firstName;
    @Schema(description = "Last name")
    String lastName;
    @Schema(description = "Email")
    String email;
    @Schema(description = "Phone")
    String phone;
    @Schema(description = "User role")
    Role role;
    @Schema(description = "Address")
    AddressDto address;
}
