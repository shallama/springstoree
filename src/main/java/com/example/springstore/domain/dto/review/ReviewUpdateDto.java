package com.example.springstore.domain.dto.review;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Schema(name = "ReviewUpdateDto", description = "Info for update review")
public class ReviewUpdateDto {
    @Schema(description = "Comment")
    String comment;
    @Min(value = 1)
    @Max(value = 100)
    @Schema(description = "Item rate from 1 to 5")
    Integer itemRate;
}
