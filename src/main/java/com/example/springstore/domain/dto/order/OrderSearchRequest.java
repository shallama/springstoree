package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "OrderSearchRequest", description = "Info for search filtered order list")
public class OrderSearchRequest {
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
    @Schema(description = "Order status")
    Status orderStatus;
    @Schema(description = "Order completeness")
    Boolean orderCompleteness;
    @Schema(description = "Flag for check order was reviewed or not")
    Boolean isReviewed;
}
