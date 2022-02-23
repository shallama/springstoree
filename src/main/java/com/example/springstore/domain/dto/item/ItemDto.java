package com.example.springstore.domain.dto.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "ItemDto", description = "Main info about item")
public class ItemDto {
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
    @Schema(description = "item name")
    String itemName;
    @Schema(description = "Item group id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID groupId;
    @Schema(description = "Price")
    Integer price;
    @Schema(description = "Description")
    String description;
    @Schema(description = "Availability")
    Boolean availability;
}
