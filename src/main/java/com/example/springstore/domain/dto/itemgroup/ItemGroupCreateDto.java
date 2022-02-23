package com.example.springstore.domain.dto.itemgroup;

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
@Schema(name = "ItemGroupCreateDto", description = "Info for create item group")
public class ItemGroupCreateDto {
    @NotBlank
    @Schema(description = "Item group name")
    String groupName;
    @NotBlank
    @Schema(description = "Description")
    String description;
}
