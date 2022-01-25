package com.example.springstore.domain.dto.item;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemDto {
    private UUID id;
    private String itemName;
    private UUID groupId;
    private String price;
    private String description;
    private Boolean availability;
}
