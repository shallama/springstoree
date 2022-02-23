package com.example.springstore.domain.dto.item;

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
public class ItemCreateDto {
    @NotBlank
    String itemName;
    @NotNull
    UUID groupId;
    Integer price;
    @NotBlank
    String description;
    Boolean availability;
}
