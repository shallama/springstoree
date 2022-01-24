package com.example.springstore.service;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface ReviewService {
    Review get(UUID id);
    Review create(UUID itemId, UUID userId, Review review);
    Review update(UUID id, Review review);
    void delete(UUID id);
    List<Review> getReviewByItem(UUID itemId);
    List<Review> getReviewsByUser(UUID userId);
}
