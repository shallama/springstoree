package com.example.springstore.domain.dto.order;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateDto {
    @NotNull
    UUID userId;
    @NotNull
    UUID itemId;
    @Min(value = 1)
    @Max(value = 100)
    Integer itemCount;
    @NotNull
    LocalDate orderDate;
    @NotBlank
    String orderStatus;
    @AssertFalse
    Boolean orderCompleteness;
    Integer amount;
}
