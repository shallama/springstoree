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

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {
    @InjectMocks
    private OrderServiceImpl orderServiceImpl;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private OrderMapper orderMapper;
    @Mock
    private UserServiceImpl userService;
    @Mock
    private ItemService itemService;
    @Mock
    private ReviewService reviewService;
    @Mock
    private DateService dateService;
    @Mock
    private RatingService ratingService;

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

    /*@Test
    public void successCreatedReview(){
        Order order = new Order();
        order.setIsReviewed(false);
        OrderServiceImpl service = Mockito.spy(orderServiceImpl);
        Item item = new Item();
        User user = new User();
        UUID userId = UUID.randomUUID();
        UUID itemId = UUID.randomUUID();
        UUID orderId = UUID.randomUUID();
        Review review = new Review();
        review.setItemRate(5);
        Mockito.doReturn(item).when(itemService).updateRating(itemId, review.getItemRate());
        Mockito.doReturn(order).when(service).get(orderId);
        Mockito.when(userService.get(userId)).thenReturn(user);
        Mockito.doReturn(order).when(service).update(orderId, order);
        Review gotReview = orderServiceImpl.createReview(itemId, userId, orderId, review);
        Assertions.assertEquals(gotReview, review);
    }*/
}
