package com.example.springstore.domain.dto.item;

import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.entity.ItemGroup;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemInfoDto {
    UUID id;
    String itemName;
    ItemGroupDto itemGroup;
    Integer price;
    String description;
    Boolean availability;
}
