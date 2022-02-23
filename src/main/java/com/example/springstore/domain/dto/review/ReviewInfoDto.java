package com.example.springstore.domain.dto.review;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "ReviewInfo", description = "Detailed Info about review")
public class ReviewInfoDto {
    @Schema(description = "Review id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID id;
    @Schema(description = "Item")
    Item item;
    @Schema(description = "User")
    User User;
    @Schema(description = "Review date")
    LocalDate reviewDate;
    @Schema(description = "Comment")
    String comment;
    @Schema(description = "Item rate from 1 to 5")
    Integer itemRate;
}
