package com.example.springstore.domain.dto.address;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressCreateDto {
    private String street;
    private String houseNum;
    private String country;
    private String addressIndex;
    private Integer version;
}
