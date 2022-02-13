package com.example.springstore.service;

import com.example.springstore.domain.dto.order.OrderSearchRequest;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.entity.enums.Status;
import org.springframework.data.domain.Page;
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

    /*Page<Order> getOrdersByUserId(UUID userId, Integer pageNum, Integer pageSize);
    Page<Order> getOrdersByItemId(UUID itemId, Integer pageNum, Integer pageSize);
    Page<Order> getOrdersByStatus(Integer pageNum, Integer pageSize, Status status);
    Page<Order> getOrdersByComplete(Integer pageNum, Integer pageSize, Boolean completeness);
    Page<Order> getOrdersByUserIdAndComplete(UUID userId, Boolean completeness, Integer pageNum, Integer pageSize);
    Page<Order> getOrdersByUserIdAndStatus(UUID userId,Status status, Integer pageNum, Integer pageSize);
    Page<Order> getOrdersByUserIdAndReviewed(UUID userId, Boolean isReviewed,  Integer pageNum, Integer pageSize);*/
    Page<Order> getOrdersList(OrderSearchRequest searchRequest, Integer pageNum, Integer pageSize);
}
