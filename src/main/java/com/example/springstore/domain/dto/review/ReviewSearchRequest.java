package com.example.springstore.domain.dto.review;

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
@Schema(name = "ReviewSearchRequest", description = "Info for search filtered review list")
public class ReviewSearchRequest {
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
    @Schema(description = "Item rate from 1 to 5")
    Integer rate;
}
