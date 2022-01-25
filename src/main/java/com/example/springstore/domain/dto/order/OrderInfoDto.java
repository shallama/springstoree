package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.User;
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
public class OrderInfoDto {
    private UUID id;
    private User user;
    private Item item;
    private Integer itemCount;
    private String orderDate;
    private String orderStatus;
    private Boolean orderCompleteness;
    private Double orderAmount;
}
