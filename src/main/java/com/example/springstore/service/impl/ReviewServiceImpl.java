package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.Rating;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.exeption.*;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.repository.OrderRepository;
import com.example.springstore.repository.ReviewRepository;
import com.example.springstore.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final RatingService ratingService;
    private final DateService dateService;

    @Override
    public Review get(UUID id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    @Transactional
    public Review create(Review review) {
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review update(UUID id, Review review) {
        LocalDate date = dateService.get();
        Period period = Period.between(get(id).getReviewDate(), date);
        Integer days = period.getDays();
        if (days < 1){
            return Optional.of(id)
                    .map(this::get)
                    .map(current -> reviewMapper.merge(current, review))
                    .map(reviewRepository::save)
                    .orElseThrow();
        }
        else {
            throw new ReviewCantUpdateException();
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public Page<Review> getReviewByItem(Item item, Pageable pageable) {
        return reviewRepository.findAllByItem(item, pageable);
    }

    @Override
    public Page<Review> getReviewsByUser(UUID userId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return reviewRepository.findAllByUser(userService.get(userId), pageable);
    }

    @Override
    public Page<Review> getReviewByRate(Integer rate, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("reviewDate").descending());
        return reviewRepository.findAllByItemRate(rate, pageable);
    }
}
