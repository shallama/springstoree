package com.example.springstore.controller;

import com.example.springstore.domain.dto.order.*;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.entity.enums.Status;
import com.example.springstore.domain.mapper.OrderMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
/**
 *  Controller for work with order information
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
@Tag(name = "Order", description = "Controller for work with order")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Order not found")
@ApiResponse(responseCode = "401", description = "Unauthorized user")
public class OrderController {
    /** Order service injecting     */
    private final OrderService orderService;
    /** Order mapper injecting     */
    private final OrderMapper orderMapper;
    /** Review mapper injecting     */
    private final ReviewMapper reviewMapper;

    /**
     * Return item by id on Json format
     * @param id
     * @return order on Json format
     */
    @GetMapping("/{orderId}")
    @Operation(description = "Find order by id")
    @ApiResponse(responseCode = "200", description = "Order found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public OrderDto get(@PathVariable(name = "orderId") UUID id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    /**
     * Return order with detailed information by id on Json format
     * @param id
     * @return order on Json format
     */
    @GetMapping("/info/{orderId}")
    @Operation(description = "Find order info by id")
    @ApiResponse(responseCode = "200", description = "Order info found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public OrderInfoDto getInfo(@PathVariable(name = "orderId") UUID id){
        return Optional.of(id)
                .map(orderService::get)
                .map(orderMapper::toInfoDto)
                .orElseThrow();
    }

    /**
     * Return pageable filtered order list
     * @param searchRequest
     * @param pageable
     * @return pageable filtered order list
     */
    @GetMapping
    @Operation(description = "Find pageable filtered order list")
    @ApiResponse(responseCode = "200", description = "Order list found")
    @PreAuthorize("hasRole('ADMIN') || hasRole('CUSTOMER') || hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<OrderDto> getOrdersList(@RequestBody OrderSearchRequest searchRequest,
                                        Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> orderService.getOrdersList(curr, pageable))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    /**
     * Create and return created order
     * @param createDto
     * @return created order on Json format
     */
    @PostMapping
    @Operation(description = "Create order if user has address")
    @ApiResponse(responseCode = "200", description = "Order created")
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

    /**
     * Update information about order and return updated order on Json format
     * @param id
     * @param updateDto
     * @return updated order on Json format
     */
    @PatchMapping("/{orderId}")
    @Operation(description = "Update order by id")
    @ApiResponse(responseCode = "200", description = "Order updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public OrderDto update(@PathVariable(name = "orderId") UUID id,
                                @RequestBody OrderUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(orderMapper::fromUpdateDto)
                .map(current -> orderService.update(id, current))
                .map(orderMapper::toDto)
                .orElseThrow();
    }

    /**
     * Delete order
     * @param id
     */
    @DeleteMapping("/{orderId}")
    @Operation(description = "Delete order by id")
    @ApiResponse(responseCode = "204", description = "Order deleted")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER')")
    public void delete(@PathVariable(name = "orderId") UUID id){
        orderService.delete(id);
    }

    /**
     * Create review and return created review
     * @param itemId
     * @param orderId
     * @param createDto
     * @return created review on Json format
     */
    @PostMapping("/{orderId}/reviews/{itemId}")
    @Operation(description = "Create review if there are order with item")
    @ApiResponse(responseCode = "200", description = "Review updated")
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
