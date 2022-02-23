package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

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
@Schema(name = "OrderDto", description = "Main info about order")
public class OrderDto {
    @Schema(description = "Order id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID userId;
    @Schema(description = "Item id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID itemId;
    @Schema(description = "Item count")
    Integer itemCount;
    @Schema(description = "Order date")
    LocalDate orderDate;
    @Schema(description = "Order status")
    Status orderStatus;
    @Schema(description = "Order completeness")
    Boolean orderCompleteness;
    @Schema(description = "Order amount")
    Integer amount;
    @Schema(description = "Flag for check order was reviewed or not")
    Boolean isReviewed;
}
