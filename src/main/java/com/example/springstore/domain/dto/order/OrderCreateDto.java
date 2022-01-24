package com.example.springstore.domain.dto.order;

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
public class OrderCreateDto {
    private UUID userId;
    private UUID itemId;
    private UUID addressId;
    private Integer itemCount;
    private String orderDate;
    private String orderStatus;
    private Boolean orderCompleteness;
    private Double orderAmount;
}
