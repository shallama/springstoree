package com.example.springstore.domain.dto.order;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.User;
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
public class OrderInfoDto {
    UUID id;
    User user;
    Item item;
    Integer itemCount;
    LocalDate orderDate;
    String orderStatus;
    Boolean orderCompleteness;
    Integer amount;
}
