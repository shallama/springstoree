package com.example.springstore.service;

import com.example.springstore.domain.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface RatingService {
    List<Rating> get(UUID itemId);
    Rating create(Rating rating);
}
