package com.example.springstore.domain.dto.itemgroup;

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
@Schema(name = "ItemGroupDto", description = "Main info about item group")
public class ItemGroupDto {
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
    @Schema(description = "Item group name")
    String groupName;
    @Schema(description = "Description")
    String description;
}
