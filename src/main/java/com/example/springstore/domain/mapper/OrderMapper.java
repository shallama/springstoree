package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.order.OrderCreateDto;
import com.example.springstore.domain.dto.order.OrderDto;
import com.example.springstore.domain.dto.order.OrderInfoDto;
import com.example.springstore.domain.dto.order.OrderUpdateDto;
import com.example.springstore.domain.entity.Order;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 *  Mapper for order
 *  @author tagir
 *  @since 15.01.2022
 */
@Mapper
public interface OrderMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "item", ignore = true)
    Order fromCreateDto(OrderCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "itemCount", ignore = true)
    @Mapping(target = "orderDate", ignore = true)
    @Mapping(target = "amount", ignore = true)
    @Mapping(target = "isReviewed", ignore = true)
    Order fromUpdateDto(OrderUpdateDto source);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "itemId", source = "item.id")
    OrderDto toDto(Order source);

    OrderInfoDto toInfoDto(Order source);

    default Page<OrderDto> toListDto(Page<Order> source){
        return source.map(this::toDto);
    }

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Order merge(@MappingTarget Order target, Order source);
}
