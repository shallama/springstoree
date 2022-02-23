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
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final ReviewMapper reviewMapper;

    @GetMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public OrderDto get(@PathVariable(name = "orderId") UUID id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/info/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public OrderInfoDto getInfo(@PathVariable(name = "orderId") UUID id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toInfoDto)
                .orElseThrow();
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') || hasRole('CUSTOMER') || hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<OrderDto> getOrdersList(@RequestBody OrderSearchRequest searchRequest,
                                        Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> orderService.getOrdersList(curr, pageable))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public OrderDto update(@PathVariable(name = "orderId") UUID id,
                                @RequestBody OrderUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(orderMapper::fromUpdateDto)
                .map(current -> orderService.update(id, current))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public void delete(@PathVariable(name = "orderId") UUID id){
        orderService.delete(id);
    }

    @PostMapping("/{orderId}/reviews/{itemId}")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
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
