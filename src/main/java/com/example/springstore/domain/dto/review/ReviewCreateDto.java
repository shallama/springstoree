package com.example.springstore.domain.dto.review;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.UUID;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ReviewCreateDto {
    UUID itemId;
    @NotNull
    UUID userId;
    @NotNull
    LocalDate reviewDate;
    @NotBlank
    String comment;
    @Min(value = 1)
    @Max(value = 5)
    Integer itemRate;
}
