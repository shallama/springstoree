package com.example.springstore.unit;

import com.example.springstore.domain.dto.order.OrderSearchRequest;
import com.example.springstore.domain.entity.*;
import com.example.springstore.domain.entity.enums.Status;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.repository.OrderRepository;
import com.example.springstore.service.DateService;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.RatingService;
import com.example.springstore.service.ReviewService;
import com.example.springstore.service.impl.OrderServiceImpl;
import com.example.springstore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.awt.event.MouseListener;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;
/**
 * Test for order service
 * @author tagir
 * @since 20.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private UserServiceImpl userService;

    /**
     * When address was not found
     */
    @Test
    public void addressNotFound(){
        Order order = new Order();
        UUID userId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        User user = new User();
        Mockito.when(userService.get(userId)).thenReturn(user);
        try{
            orderServiceImpl.create(order, userId, itemId);
            fail("");
        } catch (RuntimeException ex){
            Assertions.assertEquals("Address not found!", ex.getMessage());
        }
    }

    /**
     * When review was added on current order
     */
    @Test
    public void failedCreateReview(){
        Order order = new Order();
        order.setIsReviewed(true);
        UUID userId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        Review review = new Review();
        Mockito.when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        try{
            orderServiceImpl.createReview(itemId, userId, orderId, review);
            fail("");
        } catch (RuntimeException ex){
            Assertions.assertEquals("Review was added for this order!", ex.getMessage());
        }
    }

    /**
     * When client want to get order list by different parameters
     */
    @Test
    public void getOrderListWithAllArguments(){
        Pageable pageable = PageRequest.of(0, 50);
        UUID userId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        OrderSearchRequest searchRequest = OrderSearchRequest.builder()
                .userId(userId)
                .itemId(itemId)
                .orderStatus(Status.PREPARATION)
                .orderCompleteness(true)
                .isReviewed(true)
                .build();
        orderServiceImpl.getOrdersList(searchRequest, pageable);
    }

}
