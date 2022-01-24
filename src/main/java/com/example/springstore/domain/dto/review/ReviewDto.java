package com.example.springstore.domain.dto.review;

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
public class ReviewDto {
    private UUID id;
    private UUID itemId;
    private UUID userId;
    private String reviewDate;
    private String comment;
    private Integer itemRate;
}
