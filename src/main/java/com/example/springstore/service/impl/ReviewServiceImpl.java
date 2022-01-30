package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.exeption.OrderCantDeleteException;
import com.example.springstore.domain.exeption.ReviewCantUpdateException;
import com.example.springstore.domain.exeption.ReviewNotFoundException;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.repository.ReviewRepository;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.ReviewService;
import com.example.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final ItemService itemService;
    private final UserService userService;
    private final SimpleDateFormat dateFormat;

    @Override
    public Review get(UUID id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    @Transactional
    public Review create(UUID itemId, UUID userId, Review review) {
        review.setItem(itemService.get(itemId));
        review.setUser(userService.get(userId));
        return reviewRepository.save(review);
    }

    @Override
    @Transactional
    public Review update(UUID id, Review review) {
        LocalDate date = LocalDate.now();
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
            return null;
        }
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public List<Review> getReviewByItem(UUID itemId) {
        return reviewRepository.findAllByItem(itemService.get(itemId));
    }

    @Override
    public List<Review> getReviewsByUser(UUID userId) {
        return reviewRepository.findAllByUser(userService.get(userId));
    }
}
