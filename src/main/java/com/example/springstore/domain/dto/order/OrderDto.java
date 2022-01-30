package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.enums.Status;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {
    UUID id;
    UUID userId;
    UUID itemId;
    Integer itemCount;
    LocalDate orderDate;
    Status orderStatus;
    Boolean orderCompleteness;
    Integer amount;
}
