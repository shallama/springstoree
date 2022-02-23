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
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @GetMapping("/{reviewId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ReviewDto get(@PathVariable(name = "reviewId") UUID id){
        return Optional.of(id)
                .map(reviewService::get)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @PatchMapping("/{reviewId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public ReviewDto update(@Valid @PathVariable(name = "reviewId")UUID id, @RequestBody ReviewUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(reviewMapper::fromUpdateDto)
                .map(current -> reviewService.update(id, current))
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ReviewCantUpdateException());
    }

    @DeleteMapping("/{reviewId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public void delete(@PathVariable(name = "reviewId") UUID id){
        reviewService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<ReviewDto> getReviewList(@RequestBody ReviewSearchRequest searchRequest,
                                         Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> reviewService.getReviewList(searchRequest, pageable))
                .map(reviewMapper::listToDto)
                .orElseThrow();
    }

}
