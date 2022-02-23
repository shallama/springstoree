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
/**
 *  Service for work with order entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface OrderService {
    /**
     * Return order information by id
     * @param id
     * @return order information by id
     */
    Order get(UUID id);

    /**
     * Create and return order if the user has address
     * @param order
     * @param userId
     * @param itemId
     * @return created order
     */
    Order create(Order order, UUID userId, UUID itemId);

    /**
     * Update order and return
     * @param id
     * @param order
     * @return updated order
     */
    Order update(UUID id, Order order);

    /**
     * Delete order by id if order don't have PREPARATION order status
     * and a day has not passed since the order was created
     * @param id
     */
    void delete(UUID id);

    /**
     * Create review if order didn't have review before.
     * Order will be reviewed after create review.
     * Rate which user left will be saved.
     * @param itemId
     * @param userId
     * @param orderId
     * @param review
     * @return created review
     */
    Review createReview(UUID itemId, UUID userId, UUID orderId, Review review);

    /**
     * Return the pageable filtered order list
     * @param searchRequest contain parameters for use filter
     * @param pageable information by page number, size and sorting
     * @return filtered pageable of order list
     */
    Page<Order> getOrdersList(OrderSearchRequest searchRequest, Pageable pageable);
}
