package com.example.springstore.domain.dto.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateDto {
    @NotNull
    private UUID userId;
    @NotNull
    private UUID itemId;
    @Size(min = 1, max = 100)
    private Integer itemCount;
    @NotBlank
    private String orderDate;
    @NotBlank
    private String orderStatus;
    @AssertFalse
    private Boolean orderCompleteness;
    @Positive
    private Double orderAmount;
}
