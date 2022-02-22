package com.example.springstore.domain.dto.item;

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
public class ItemSearchRequest {
    UUID groupId;
    Integer maxPrice;
    Boolean availability;
    Integer rating;
}
