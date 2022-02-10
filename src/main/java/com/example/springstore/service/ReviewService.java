package com.example.springstore.service;

import com.example.springstore.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface ReviewService {
    Review get(UUID id);
    Review create(UUID itemId, UUID userId, UUID orderId, Review review);
    Review update(UUID id, Review review);
    void delete(UUID id);
    Page<Review> getReviewByItem(UUID itemId, Integer pageNum, Integer pageSize);
    Page<Review> getReviewsByUser(UUID userId, Integer pageNum, Integer pageSize);
    Page<Review> getReviewByRate(Integer rate, Integer pageNum, Integer pageSize);
}
