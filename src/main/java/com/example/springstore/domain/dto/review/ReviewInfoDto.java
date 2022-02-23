package com.example.springstore.domain.dto.review;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.User;
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
public class ReviewInfoDto {
    UUID id;
    Item item;
    User User;
    LocalDate reviewDate;
    String comment;
    Integer itemRate;
}
