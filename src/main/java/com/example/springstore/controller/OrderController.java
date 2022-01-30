package com.example.springstore.controller;

import com.example.springstore.domain.dto.order.OrderCreateDto;
import com.example.springstore.domain.dto.order.OrderDto;
import com.example.springstore.domain.dto.order.OrderInfoDto;
import com.example.springstore.domain.dto.order.OrderUpdateDto;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.service.OrderService;
import com.example.springstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @GetMapping("/{orderId}")
    public OrderDto get(@PathVariable(name = "orderId") UUID id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{orderId}")
    public OrderInfoDto getInfo(@PathVariable(name = "orderId") UUID id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toInfoDto)
                .orElseThrow();
    }

    @GetMapping("/user/{userId}")
    public List<OrderDto> getOrdersByUserId(@PathVariable(name = "userId") UUID userId){
        return Optional.of(userId)
                .map(orderService::getOrdersByUserId)
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/user/{itemId}")
    public List<OrderDto> getOrdersByItemId(@PathVariable(name = "itemId") UUID itemId){
        return Optional.of(itemId)
                .map(orderService::getOrdersByItemId)
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @PostMapping
    public OrderDto create(@Valid @RequestBody OrderCreateDto createDto){
        UUID userId = createDto.getUserId();
        UUID itemId = createDto.getItemId();
        return Optional.ofNullable(createDto)
                .map(orderMapper::fromCreateDto)
                .map(current -> orderService.create(current, userId, itemId))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{orderId}")
    public OrderDto update(@PathVariable(name = "orderId") UUID id,
                                @RequestBody OrderUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(orderMapper::fromUpdateDto)
                .map(current -> orderService.update(id, current))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{orderId}")
    public void delete(@PathVariable(name = "orderId") UUID id){
        orderService.delete(id);
    }

}
