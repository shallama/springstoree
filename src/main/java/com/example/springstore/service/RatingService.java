package com.example.springstore.service;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 *  Service for work with rating entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface RatingService {
    /**
     * Return rating information by id
     * @param id
     * @return rating information
     */
    Rating get(UUID id);

    /**
     * Create and return ratings
     * @param rating
     * @return created rating
     */
    Rating create(Rating rating);

    /**
     * Update rating information
     * @param rating
     * @return updated rating
     */
    Rating update(Rating rating);

    /**
     * delete rating by rating id
     * @param id
     */
    void delete(UUID id);

    /**
     * Return Rating by item
     * @param item
     * @return rating by item
     */
    Rating getByItem(Item item);
}
