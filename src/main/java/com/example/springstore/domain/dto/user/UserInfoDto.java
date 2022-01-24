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
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private AddressDto address;
}
