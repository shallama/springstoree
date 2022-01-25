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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Service
@Primary
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderMapper orderMapper;
    @Autowired
    private final UserServiceImpl userService;
    @Autowired
    private final ItemService itemService;
    @Autowired
    private final AddressService addressService;
    @Autowired
    private final SimpleDateFormat dateFormat;

    @SneakyThrows
    @Override
    public Order get(UUID id) {
        return orderRepository.findById(id).orElseThrow();
    }

    @Override
    public Order create(Order order, UUID userId, UUID itemId) {
        order.setUser(userService.get(userId));
        order.setItem(itemService.get(itemId));
        return orderRepository.save(order);
    }

    @SneakyThrows
    @Override
    public Order update(UUID id, Order order) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> orderMapper.merge(current, order))
                .map(orderRepository::save)
                .orElseThrow();
    }

    @SneakyThrows
    private Long getHoursNum(String date) throws ParseException {
        Date date1 = dateFormat.parse(date);
        Date date2 = new Date();
        long diff = date2.getTime() - date1.getTime();
        long hours = TimeUnit.MILLISECONDS.toHours(diff);
        return hours;
    }

    @SneakyThrows
    @Override
    public void delete(UUID id) {
        Order order = get(id);
        if (order.getOrderStatus().equals("preparation") && getHoursNum(order.getOrderDate()) < 24)
            orderRepository.deleteById(id);
        else
            throw new OrderCantDeleteException();
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
