package com.example.springstore.controller;

import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.dto.review.ReviewSearchRequest;
import com.example.springstore.domain.dto.review.ReviewUpdateDto;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.exeption.ReviewCantUpdateException;
import com.example.springstore.domain.exeption.ReviewNotFoundException;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.repository.ReviewRepository;
import com.example.springstore.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
/**
 *  Controller for work with review information
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Tag(name = "Review", description = "Controller for work with review")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Review not found")
@ApiResponse(responseCode = "401", description = "Unauthorized user")
public class ReviewController {

    /** Review mapper injecting     */
    private final ReviewMapper reviewMapper;
    /** Review service injecting     */
    private final ReviewService reviewService;

    /**
     * Return review by id
     * @param id
     * @return review on Json format
     */
    @GetMapping("/{reviewId}")
    @Operation(description = "Find review by id")
    @ApiResponse(responseCode = "200", description = "Review found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ReviewDto get(@PathVariable(name = "reviewId") UUID id){
        return Optional.of(id)
                .map(reviewService::get)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    /**
     * Update review and return updated review
     * @param id
     * @param updateDto
     * @return updated reveiw on Json format
     */
    @PatchMapping("/{reviewId}")
    @Operation(description = "Update review by id")
    @ApiResponse(responseCode = "200", description = "Review updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public ReviewDto update(@Valid @PathVariable(name = "reviewId")UUID id, @RequestBody ReviewUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(reviewMapper::fromUpdateDto)
                .map(current -> reviewService.update(id, current))
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ReviewCantUpdateException());
    }

    /**
     * Delete review
     * @param id
     */
    @DeleteMapping("/{reviewId}")
    @Operation(description = "Delete review by id")
    @ApiResponse(responseCode = "204", description = "Review deleted")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public void delete(@PathVariable(name = "reviewId") UUID id){
        reviewService.delete(id);
    }

    /**
     * Return pageable filtered review list
     * @param searchRequest
     * @param pageable
     * @return pageable filtered review list
     */
    @GetMapping
    @Operation(description = "Find pageable filtered review list")
    @ApiResponse(responseCode = "200", description = "Review list found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<ReviewDto> getReviewList(@RequestBody ReviewSearchRequest searchRequest,
                                         Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> reviewService.getReviewList(searchRequest, pageable))
                .map(reviewMapper::listToDto)
                .orElseThrow();
    }

}
