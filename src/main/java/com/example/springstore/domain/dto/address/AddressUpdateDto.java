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
@Schema(name = "AddressUpdateDto", description = "Info for update address")
public class AddressUpdateDto {
    @Schema(description = "City")
    String city;
    @Schema(description = "Street")
    String street;
    @Schema(description = "House number")
    String houseNum;
    @Schema(description = "Country")
    String country;
    @Schema(description = "Address index")
    String addressIndex;
}
