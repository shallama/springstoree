package com.example.springstore.domain.dto.itemgroup;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotBlank;

@Value
@Builder
@Jacksonized
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ItemGroupCreateDto {
    @NotBlank
    private String groupName;
    @NotBlank
    private String description;
}
