package com.example.springstore.domain.dto.itemgroup;

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
public class ItemGroupDto {
    UUID id;
    String groupName;
    String description;
}
