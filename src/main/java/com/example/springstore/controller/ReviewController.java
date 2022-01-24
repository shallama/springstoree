package com.example.springstore.controller;

import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.dto.review.ReviewUpdateDto;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.repository.ReviewRepository;
import com.example.springstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private final ReviewMapper reviewMapper;
    @Autowired
    private final ReviewService reviewService;

    @SneakyThrows
    @PatchMapping("/{reviewId}")
    public ReviewDto update(@PathVariable(name = "reviewId")UUID id, @RequestBody ReviewUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(reviewMapper::fromUpdateDto)
                .map(current -> reviewService.update(id, current))
                .map(reviewMapper::toDto)
                .orElseThrow();
    }
    @SneakyThrows
    @DeleteMapping("/{reviewId}")
    public void delete(@PathVariable(name = "reviewId") UUID id){
        reviewService.delete(id);
    }

}
