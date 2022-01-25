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
    private UUID id;
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
