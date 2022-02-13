package com.example.springstore.controller;

import com.example.springstore.domain.dto.order.*;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.entity.enums.Status;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ReviewMapper reviewMapper;

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

    @GetMapping("/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersList(@RequestBody OrderSearchRequest searchRequest,
                                        @PathVariable(name = "pageNum") Integer pageNum,
                                        @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.ofNullable(searchRequest)
                .map(curr -> orderService.getOrdersList(curr, pageNum, pageSize))
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

    @PostMapping("/{orderId}/reviews/{itemId}")
    public ReviewDto createReview(@Valid @PathVariable(name = "itemId") UUID itemId,
                                  @PathVariable(name = "orderId") UUID orderId,
                                  @RequestBody ReviewCreateDto createDto){
        return Optional.ofNullable(createDto)
                .map(reviewMapper::fromCreateDto)
                .map(current -> orderService.createReview(itemId,
                        createDto.getUserId(), orderId, current))
                .map(reviewMapper::toDto)
                .orElseThrow();
    }

}
