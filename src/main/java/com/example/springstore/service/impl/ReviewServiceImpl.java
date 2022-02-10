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
    private final ItemService itemService;
    private final UserService userService;
    private final RatingService ratingService;
    private final OrderService orderService;

    @Override
    public Review get(UUID id) {
        return reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException(id));
    }



    @Override
    @Transactional
    public Review create(UUID itemId, UUID userId, UUID orderId, Review review) {
        Order order = orderService.get(orderId);
        if (order.getIsReviewed()){
            throw new ReviewWasAddedException();
        }
        Item item = itemService.get(itemId);
        Rating rating = new Rating();
        rating.setItem(item);
        rating.setRate(review.getItemRate());
        ratingService.create(rating);
        order.setIsReviewed(true);
        orderService.update(orderId, order);
        review.setItem(item);
        review.setUser(userService.get(userId));
        return reviewRepository.save(review);
    }

    /*private Integer getUpdatedItemRate(Integer oldNum, Integer newNum){
        if (oldNum == null) {
            return newNum;
        }
        return (oldNum + newNum) / 2;
    }*/

    /*@Override
    @Transactional
    public Review create(UUID itemId, UUID userId, Review review) {
        Item item = itemService.get(itemId);
        Integer rating = getUpdatedItemRate(item.getItemRate(), review.getItemRate());
        item.setItemRate(rating);
        Item updatedItem = itemService.update(itemId, item);
        if (updatedItem != null){
            review.setItem(updatedItem);
            review.setUser(userService.get(userId));
            return reviewRepository.save(review);
        } else {
            throw new ReviewCanNotCreateException();
        }
    }*/

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
    public Page<Review> getReviewByItem(UUID itemId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("itemRate").descending());
        Item item = itemService.get(itemId);
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
