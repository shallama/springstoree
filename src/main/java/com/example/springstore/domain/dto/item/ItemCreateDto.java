package com.example.springstore.domain.dto.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.AssertFalse;
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
@Schema(name = "ItemCreateDto", description = "Info for create item")
public class ItemCreateDto {
    @NotBlank
    @Schema(description = "item name")
    String itemName;

    @NotNull
    @Schema(description = "Item group id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID groupId;

    @Schema(description = "Price")
    Integer price;
    @NotBlank
    @Schema(description = "Description")
    String description;
    @Schema(description = "Availability")
    Boolean availability;
}
