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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
@Service
@Primary
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    private final ReviewMapper reviewMapper;
    @Autowired
    private final ReviewRepository reviewRepository;
    @Autowired
    private final ItemService itemService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final SimpleDateFormat dateFormat;

    @SneakyThrows
    @Override
    public Review get(UUID id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }

    @Override
    public Review create(UUID itemId, UUID userId, Review review) {
        review.setItem(itemService.get(itemId));
        review.setUser(userService.get(userId));
        return reviewRepository.save(review);
    }

    @SneakyThrows
    private Long checkDate(String date) throws ParseException {
        Date date1 = dateFormat.parse(date);
        Date date2 = new Date();
        long diff = date2.getTime() - date1.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        return hours;
    }

    @SneakyThrows
    @Override
    public Review update(UUID id, Review review) {
        if (checkDate(get(id).getReviewDate()) < 2)
            return Optional.of(id)
                    .map(this::get)
                    .map(current -> reviewMapper.merge(current, review))
                    .map(reviewRepository::save)
                    .orElseThrow();
        else
            return null;
    }

    @Override
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
