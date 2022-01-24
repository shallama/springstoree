package com.example.springstore.service;

import com.example.springstore.domain.entity.Order;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public interface OrderService {
    Order get(UUID id);
    Order create(Order order, UUID userId, UUID itemId, UUID addressId);
    Order update(UUID id, Order order);
    void delete(UUID id);
    List<Order> getOrdersByUserId(UUID userId);
    List<Order> getOrdersByItemId(UUID itemId);
}
