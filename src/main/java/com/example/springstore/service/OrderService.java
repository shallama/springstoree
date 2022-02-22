package com.example.springstore.service;

import com.example.springstore.domain.dto.order.OrderSearchRequest;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    Order get(UUID id);
    Order create(Order order, UUID userId, UUID itemId);
    Order update(UUID id, Order order);
    void delete(UUID id);
    Review createReview(UUID itemId, UUID userId, UUID orderId, Review review);
    Page<Order> getOrdersList(OrderSearchRequest searchRequest, Pageable pageable);
}
