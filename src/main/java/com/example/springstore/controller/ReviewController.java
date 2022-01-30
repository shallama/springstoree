package com.example.springstore.controller;

import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewMapper reviewMapper;
    private final ReviewService reviewService;

    @GetMapping("/{reviewId}")
    public ReviewDto get(@PathVariable(name = "reviewId") UUID id){
        return Optional.of(id)
                .map(reviewService::get)
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @PatchMapping("/{reviewId}")
    public ReviewDto update(@Valid @PathVariable(name = "reviewId")UUID id, @RequestBody ReviewUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(reviewMapper::fromUpdateDto)
                .map(current -> reviewService.update(id, current))
                .map(reviewMapper::toDto)
                .orElseThrow(() -> new ReviewCantUpdateException());
    }

    @DeleteMapping("/{reviewId}")
    public void delete(@PathVariable(name = "reviewId") UUID id){
        reviewService.delete(id);
    }

}
