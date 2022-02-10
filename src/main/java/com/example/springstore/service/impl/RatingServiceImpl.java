package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Rating;
import com.example.springstore.repository.RatingRepository;
import com.example.springstore.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public List<Rating> get(UUID itemId) {
        return ratingRepository.findAllByItem(itemId);
    }

    @Override
    @Transactional
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }
}
