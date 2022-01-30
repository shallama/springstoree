package com.example.springstore.domain.dto.user;

import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.entity.Address;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInfoDto {
    String firstName;
    String lastName;
    String email;
    String phone;
    String role;
    AddressDto address;
}
