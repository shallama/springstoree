package com.example.springstore.service;

import com.example.springstore.domain.dto.review.ReviewSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReviewService {
    Review get(UUID id);
    Review create(Review review);
    Review update(UUID id, Review review);
    void delete(UUID id);
    Page<Review> getReviewList(ReviewSearchRequest searchRequest, Pageable pageable);
}
