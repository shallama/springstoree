package com.example.springstore.domain.dto.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "ItemSearchRequest", description = "Info for search filtered item list")
public class ItemSearchRequest {
    @Schema(description = "Item group id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID groupId;
    @Schema(description = "Max price for find items")
    Integer maxPrice;
    @Schema(description = "Availability")
    Boolean availability;
    @Schema(description = "Rating")
    Integer rating;
}
