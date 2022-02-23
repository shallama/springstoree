package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Rating;
import com.example.springstore.domain.exeption.ItemGroupNotFoundException;
import com.example.springstore.repository.RatingRepository;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 * Rating service implementation
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RatingServiceImpl implements RatingService {

    private final RatingRepository ratingRepository;

    @Override
    public Rating get(UUID id) {
        return ratingRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Rating create(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public Rating update(Rating rating) {
        return ratingRepository.save(rating);
    }

    @Override
    public void delete(UUID id) {
        ratingRepository.deleteById(id);
    }

    @Override
    public Rating getByItem(Item item) {
        return ratingRepository.findByItem(item);
    }
}
