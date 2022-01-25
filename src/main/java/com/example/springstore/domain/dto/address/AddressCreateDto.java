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
    private String city;
    @NotBlank
    private String street;
    @NotBlank
    private String houseNum;
    @NotBlank
    private String country;
    @NotBlank
    private String addressIndex;
}
