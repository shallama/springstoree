package com.example.springstore.service.impl;

import com.example.springstore.domain.dto.order.OrderSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.entity.enums.Status;
import com.example.springstore.domain.exeption.AddressNotFoundException;
import com.example.springstore.domain.exeption.OrderCanNotFindException;
import com.example.springstore.domain.exeption.OrderCantDeleteException;
import com.example.springstore.domain.exeption.ReviewWasAddedException;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.repository.OrderRepository;
import com.example.springstore.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.example.springstore.domain.entity.enums.Status.*;
/**
 *  Order service implementation
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserServiceImpl userService;
    private final ItemService itemService;
    private final ReviewService reviewService;
    private final DateService dateService;
    private final RatingService ratingService;

    @Override
    public Order get(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderCanNotFindException());
    }

    @Override
    @Transactional
    public Order create(Order order, UUID userId, UUID itemId) {
        User user = userService.get(userId);
        if (user.getAddress() == null){
            throw new AddressNotFoundException();
        } else {
            order.setUser(user);
            order.setItem(itemService.get(itemId));
            return orderRepository.save(order);
        }
    }

    @Override
    @Transactional
    public Order update(UUID id, Order order) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> orderMapper.merge(current, order))
                .map(orderRepository::save)
                .orElseThrow();
    }

    private Boolean validateDate(LocalDate orderDate) {
        LocalDate date = dateService.get();
        Period period = Period.between(orderDate, date);
        Integer days = period.getDays();
        return (days < 1);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Order order = get(id);
        if ((order.getOrderStatus() == PREPARATION) && validateDate(order.getOrderDate())){
            orderRepository.deleteById(id);
        }
        else {
            throw new OrderCantDeleteException();
        }
    }

    @Override
    @Transactional
    public Review createReview(UUID itemId, UUID userId, UUID orderId, Review review) {
        Order order = get(orderId);
        if (order.getIsReviewed()){
            throw new ReviewWasAddedException();
        }
        Item item = itemService.updateRating(itemId, review.getItemRate());
        order.setIsReviewed(true);
        update(orderId, order);
        review.setItem(item);
        User user = userService.get(userId);
        review.setUser(user);
        return reviewService.create(review);
    }


    @Override
    public Page<Order> getOrdersList(OrderSearchRequest searchRequest, Pageable pageable) {
        Specification<Order> specification = (Specification<Order>) (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();

            if (searchRequest.getUserId() != null){
                Join<Order, User> userJoin = root.join("user");
                User user = userService.get(searchRequest.getUserId());
                Predicate userPredicate = criteriaBuilder.equal(root.get("user"), user);
                predicatesList.add(userPredicate);
            }
            if (searchRequest.getItemId() != null){
                Join<Order, Item> itemJoin = root.join("item");
                Item item = itemService.get(searchRequest.getItemId());
                Predicate itemPredicate = criteriaBuilder.equal(root.get("item"),item);
                predicatesList.add(itemPredicate);
            }
            if (searchRequest.getOrderStatus() != null){
                Predicate statusPredicate = criteriaBuilder.equal(root.get("orderStatus"), searchRequest.getOrderStatus());
                predicatesList.add(statusPredicate);
            }
            if (searchRequest.getOrderCompleteness() != null){
                Predicate completePredicate = criteriaBuilder.equal(root.get("orderCompleteness"), searchRequest.getOrderCompleteness());
                predicatesList.add(completePredicate);
            }
            if (searchRequest.getIsReviewed() != null){
                Predicate reviewedPredicate = criteriaBuilder.equal(root.get("isReviewed"), searchRequest.getIsReviewed());
                predicatesList.add(reviewedPredicate);
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        };
        return orderRepository.findAll(specification, pageable);
    }
}
