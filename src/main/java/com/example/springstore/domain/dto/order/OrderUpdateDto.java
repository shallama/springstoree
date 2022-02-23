package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "OrderUpdateDto", description = "Info for update order")
public class OrderUpdateDto {
    @Schema(description = "Order status")
    Status orderStatus;
    @Schema(description = "Order completeness")
    Boolean orderCompleteness;
}
