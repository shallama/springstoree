package com.example.springstore.domain.dto.address;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AddressDto {
    @NotNull
    UUID id;
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
