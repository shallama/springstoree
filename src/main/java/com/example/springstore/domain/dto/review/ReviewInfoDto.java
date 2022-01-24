package com.example.springstore.domain.dto.review;

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
public class ReviewInfoDto {
    private UUID id;
    private Item item;
    private User User;
    private String reviewDate;
    private String comment;
    private Integer itemRate;
}
