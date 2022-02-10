package com.example.springstore.repository;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {
    Page<Order> findAllByItem(Item item, Pageable pageable);
    Page<Order> findAllByUser(User user, Pageable pageable);
    Page<Order> findAllByOrderStatus(Status status, Pageable pageable);
    Page<Order> findAllByOrderCompleteness(Boolean completeness, Pageable pageable);
    Page<Order> findAllByUserAndOrderCompleteness(User user, Boolean orderCompleteness, Pageable pageable);
    Page<Order> findAllByUserAndOrderStatus(User user, Status status, Pageable pageable);
    Page<Order> findAllByUserAndIsReviewed(User user, Boolean isReviewed, Pageable pageable);
}
