package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.entity.enums.Status;
import com.example.springstore.domain.exeption.AddressNotFoundException;
import com.example.springstore.domain.exeption.OrderCanNotFindException;
import com.example.springstore.domain.exeption.OrderCantDeleteException;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.repository.OrderRepository;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

import static com.example.springstore.domain.entity.enums.Status.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserServiceImpl userService;
    private final ItemService itemService;

    @Override
    public Order get(UUID id) {
        return orderRepository.findById(id).orElseThrow(() -> new OrderCanNotFindException());
    }

    @Override
    @Transactional
    public Order create(Order order, UUID userId, UUID itemId) {
        order.setUser(userService.get(userId));
        if (order.getUser().getAddress() == null){
            throw new AddressNotFoundException();
        } else {
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
        LocalDate date = LocalDate.now();
        Period period = Period.between(orderDate, date);
        Integer days = period.getDays();
        if (days < 1)
            return true;
        else
            return false;
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
    public Page<Order> getOrdersByUserId(UUID userId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return  orderRepository.findAllByUser(userService.get(userId), pageable);
    }

    @Override
    public Page<Order> getOrdersByItemId(UUID itemId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return orderRepository.findAllByItem(itemService.get(itemId), pageable);
    }

    @Override
    public Page<Order> getOrdersByStatus(Integer pageNum, Integer pageSize, Status status) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return orderRepository.findAllByOrderStatus(status, pageable);
    }

    @Override
    public Page<Order> getOrdersByComplete(Integer pageNum, Integer pageSize, Boolean completeness) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return orderRepository.findAllByOrderCompleteness(completeness, pageable);
    }

    @Override
    public Page<Order> getOrdersByUserIdAndComplete(UUID userId, Boolean completeness, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        User user = userService.get(userId);
        return orderRepository.findAllByUserAndOrderCompleteness(user, completeness, pageable);
    }

    @Override
    public Page<Order> getOrdersByUserIdAndStatus(UUID userId, Status status, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        User user = userService.get(userId);
        return orderRepository.findAllByUserAndOrderStatus(user, status, pageable);
    }

    @Override
    public Page<Order> getOrdersByUserIdAndReviewed(UUID userId, Boolean isReviewed, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize, Sort.by("isReviewed").ascending());
        User user = userService.get(userId);
        return orderRepository.findAllByUserAndIsReviewed(user, isReviewed, pageable);
    }

    @Override
    public Page<Order> getOrdersList(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return orderRepository.findAll(pageable);
    }
}
