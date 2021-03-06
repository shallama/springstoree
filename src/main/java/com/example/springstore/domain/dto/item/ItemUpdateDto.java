package com.example.springstore.domain.dto.item;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

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
@Schema(name = "ItemUpdateDto", description = "Info for update item")
public class ItemUpdateDto {
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
