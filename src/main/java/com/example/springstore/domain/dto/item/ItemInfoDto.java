package com.example.springstore.domain.dto.item;

import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.entity.ItemGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemInfoDto {
    private UUID id;
    private String itemName;
    private ItemGroupDto itemGroup;
    private String price;
    private String description;
    private Boolean availability;
}
