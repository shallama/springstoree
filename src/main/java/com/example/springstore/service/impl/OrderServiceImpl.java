package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.BaseEntity;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.exeption.OrderCantDeleteException;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.repository.OrderRepository;
import com.example.springstore.service.AddressService;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;

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
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    @Transactional
    public Order create(Order order, UUID userId, UUID itemId) {
        order.setUser(userService.get(userId));
        order.setItem(itemService.get(itemId));
        return orderRepository.save(order);
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

    private Integer getHoursNum(LocalDate orderDate) {
        LocalDate date = LocalDate.now();
        Period period = Period.between(orderDate, date);
        Integer days = period.getDays();
        return days;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Order order = get(id);
        if (order.getOrderStatus().equals("preparation") && getHoursNum(order.getOrderDate()) < 1){
            orderRepository.deleteById(id);
        }
        else{
            throw new OrderCantDeleteException();
        }
    }

    @Override
    public List<Order> getOrdersByUserId(UUID userId) {
        return  orderRepository.findAllByUser(userService.get(userId));
    }

    @Override
    public List<Order> getOrdersByItemId(UUID itemId) {
        return orderRepository.findAllByItem(itemService.get(itemId));
    }
}
