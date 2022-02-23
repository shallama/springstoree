package com.example.springstore.domain.dto.address;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "AddressDto", description = "Main info about address")
public class AddressDto {
    @NotNull
    @Schema(description = "Address id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
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
