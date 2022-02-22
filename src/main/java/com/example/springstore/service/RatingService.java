package com.example.springstore.service;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface RatingService {
    Rating get(UUID id);
    Rating create(Rating rating);
    Rating update(Rating rating);
    Rating getByItem(Item item);
}
