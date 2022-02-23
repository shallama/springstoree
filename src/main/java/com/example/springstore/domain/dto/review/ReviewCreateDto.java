package com.example.springstore.domain.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.*;
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
@Schema(name = "ReviewCreateDto", description = "Info for create review")
public class ReviewCreateDto {
    @Schema(description = "Item id",
            required = true,
            maxLength = 36,
            minLength = 36)
    UUID itemId;
    @Schema(description = "User id",
            required = true,
            maxLength = 36,
            minLength = 36)
    @NotNull
    UUID userId;
    @NotNull
    @Schema(description = "Review date")
    LocalDate reviewDate;
    @NotBlank
    @Schema(description = "Comment")
    String comment;
    @Min(value = 1)
    @Max(value = 5)
    @Schema(description = "Item rate from 1 to 5")
    Integer itemRate;
}
