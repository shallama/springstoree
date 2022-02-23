package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "OrderCreateDto", description = "Info for create order")
public class OrderCreateDto {
    @NotNull
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID userId;

    @NotNull
    @Schema(description = "Item id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID itemId;

    @Min(value = 1)
    @Max(value = 100)
    @Schema(description = "Item count for buying")
    Integer itemCount;
    @NotNull
    @Schema(description = "Order date")
    LocalDate orderDate;
    @Schema(description = "Order status")
    Status orderStatus;
    @AssertFalse
    @Schema(description = "Order completeness")
    Boolean orderCompleteness;
    @Schema(description = "Order amount")
    Integer amount;
    @AssertFalse
    @Schema(description = "Flag for check order was reviewed or not")
    Boolean isReviewed;

}
