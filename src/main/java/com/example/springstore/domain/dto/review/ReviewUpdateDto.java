package com.example.springstore.domain.dto.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Size;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewUpdateDto {
    private String comment;
    @Size(min = 1, max = 5)
    private Integer itemRate;
}
