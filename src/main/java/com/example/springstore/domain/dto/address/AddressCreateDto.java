package com.example.springstore.domain.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "AddressCreateDto", description = "Info for create address")
public class AddressCreateDto {
    @NotBlank
    @Schema(description = "City")
    String city;
    @NotBlank
    @Schema(description = "Street")
    String street;
    @NotBlank
    @Schema(description = "House number")
    String houseNum;
    @NotBlank
    @Schema(description = "Country")
    String country;
    @NotBlank
    @Schema(description = "Address index")
    String addressIndex;
}
