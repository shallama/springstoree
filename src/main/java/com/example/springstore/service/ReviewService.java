package com.example.springstore.service;

import com.example.springstore.domain.dto.review.ReviewSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 *  Service for work with review
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface ReviewService {
    /**
     * Return review information by id
     * @param id
     * @return review information
     */
    Review get(UUID id);

    /**
     * Create and return review
     * @param review
     * @return created review
     */
    Review create(Review review);

    /**
     * Update and return review if review was created less than one day
     * @param id
     * @param review
     * @return
     */
    Review update(UUID id, Review review);

    /**
     * Delete review by id
     * @param id
     */
    void delete(UUID id);

    /**
     * Return the pageable filtered review list
     * @param searchRequest contain parameters for use filter
     * @param pageable information by page number, size and sorting
     * @return filtered pageable of review list
     */
    Page<Review> getReviewList(ReviewSearchRequest searchRequest, Pageable pageable);
}
