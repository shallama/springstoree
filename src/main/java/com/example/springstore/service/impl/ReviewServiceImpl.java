package com.example.springstore.service.impl;

import com.example.springstore.domain.dto.review.ReviewSearchRequest;
import com.example.springstore.domain.entity.*;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewServiceImpl implements ReviewService {

    private final ReviewMapper reviewMapper;
    private final ReviewRepository reviewRepository;
    private final UserService userService;
    private final DateService dateService;
    private final ItemService itemService;

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
        Review oldReview = get(id);
        Period period = Period.between(oldReview.getReviewDate(), date);
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
    public Page<Review> getReviewList(ReviewSearchRequest searchRequest,Pageable pageable) {
        Specification<Review> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (searchRequest.getUserId() != null){
                Join<Review, User> userJoin = root.join("user");
                User user = userService.get(searchRequest.getUserId());
                Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
                predicatesList.add(userPredicate);
            }
            if (searchRequest.getItemId() != null){
                Join<Review, Item> itemJoin = root.join("item");
                Item item = itemService.get(searchRequest.getItemId());
                Predicate itemPredicate = criteriaBuilder.equal(root.get("item"),item);
                predicatesList.add(itemPredicate);
            }
            if (searchRequest.getRate() != null){
                Predicate ratePredicate = criteriaBuilder.equal(root.get("itemRate"), searchRequest.getRate());
                predicatesList.add(ratePredicate);
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        };
        return reviewRepository.findAll(specification, pageable);
    }
}
