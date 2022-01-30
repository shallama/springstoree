package com.example.springstore.domain.dto.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressUpdateDto {
    String city;
    String street;
    String houseNum;
    String country;
    String addressIndex;
}
