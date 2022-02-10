package com.example.springstore.controller;

import com.example.springstore.domain.dto.order.OrderCreateDto;
import com.example.springstore.domain.dto.order.OrderDto;
import com.example.springstore.domain.dto.order.OrderInfoDto;
import com.example.springstore.domain.dto.order.OrderUpdateDto;
import com.example.springstore.domain.entity.enums.Status;
import com.example.springstore.domain.mapper.OrderMapper;
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
    public Page<OrderDto> getOrdersList(@PathVariable(name = "pageNum") Integer pageNum,
                                        @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.ofNullable(pageNum)
                .map(curr -> orderService.getOrdersList(curr, pageSize))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/user/{userId}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByUserId(@PathVariable(name = "userId") UUID userId,
                                            @PathVariable(name = "pageNum") Integer pageNum,
                                            @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.of(userId)
                .map(curr -> orderService.getOrdersByUserId(curr, pageNum, pageSize))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/user-review/{userId}/{reviewed}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByUserIdAndReviewed(@PathVariable(name = "reviewed") Boolean isReviewed,
                                                       @PathVariable(name = "pageNum") Integer pageNum,
                                                       @PathVariable(name = "pageSize") Integer pageSize,
                                                       @PathVariable(name = "userId") UUID userId){
        return Optional.of(pageNum)
                .map(curr -> orderService.getOrdersByUserIdAndReviewed(userId, isReviewed, curr, pageSize))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/user/{userId}/{completeness}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByUserIdAndComplete(@PathVariable(name = "completeness") Boolean compl,
                                                       @PathVariable(name = "pageNum") Integer pageNum,
                                                       @PathVariable(name = "pageSize") Integer pageSize,
                                                       @PathVariable(name = "userId") UUID userId){
        return Optional.of(pageNum)
                .map(curr -> orderService.getOrdersByUserIdAndComplete(userId, compl, curr, pageSize))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/user-status/{userId}/{status}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByUserIdAndStatus(@PathVariable(name = "status") Status status,
                                                     @PathVariable(name = "pageNum") Integer pageNum,
                                                     @PathVariable(name = "pageSize") Integer pageSize,
                                                     @PathVariable(name = "userId") UUID userId){
        return Optional.of(pageNum)
                .map(curr -> orderService.getOrdersByUserIdAndStatus(userId, status, curr, pageSize))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/item/{itemId}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByItemId(@PathVariable(name = "itemId") UUID itemId,
                                            @PathVariable(name = "pageNum") Integer pageNum,
                                            @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.of(itemId)
                .map(curr -> orderService.getOrdersByItemId(curr, pageNum, pageSize))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/status/{status}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByStatus(@PathVariable(name = "status") Status status,
                                            @PathVariable(name = "pageNum") Integer pageNum,
                                            @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.of(pageNum)
                .map(curr -> orderService.getOrdersByStatus(curr, pageSize, status))
                .map(orderMapper::toListDto)
                .orElseThrow();
    }

    @GetMapping("/completeness/{completeness}/{pageNum}/{pageSize}")
    public Page<OrderDto> getOrdersByCompleteness(@PathVariable(name = "completeness") Boolean compl,
                                                  @PathVariable(name = "pageNum") Integer pageNum,
                                                  @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.of(pageNum)
                .map(curr -> orderService.getOrdersByComplete(curr, pageSize, compl))
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
