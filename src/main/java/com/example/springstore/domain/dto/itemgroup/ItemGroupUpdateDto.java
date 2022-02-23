package com.example.springstore.domain.dto.itemgroup;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "ItemGroupUpdateDto", description = "Info for update item group")
public class ItemGroupUpdateDto {
    @Schema(description = "Item group name")
    String groupName;
    @Schema(description = "Description")
    String description;
}
