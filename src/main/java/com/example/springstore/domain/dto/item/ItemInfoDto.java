package com.example.springstore.domain.dto.item;

import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.entity.ItemGroup;
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
@Schema(name = "ItemInfo", description = "Detailed Info about item")
public class ItemInfoDto {
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
    @Schema(description = "Item name")
    String itemName;
    @Schema(description = "Item group")
    ItemGroupDto itemGroup;
    @Schema(description = "Price")
    Integer price;
    @Schema(description = "Description")
    String description;
    @Schema(description = "Availability")
    Boolean availability;
}
