package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.entity.enums.Status;
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
public class OrderSearchRequest {
    UUID userId;
    UUID itemId;
    Status orderStatus;
    Boolean orderCompleteness;
    Boolean isReviewed;
}
