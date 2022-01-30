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
public class AddressCreateDto {
    @NotBlank
    String city;
    @NotBlank
    String street;
    @NotBlank
    String houseNum;
    @NotBlank
    String country;
    @NotBlank
    String addressIndex;
}
