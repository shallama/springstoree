package com.example.springstore.domain.mapper;
import com.example.springstore.domain.dto.item.ItemCreateDto;
import com.example.springstore.domain.dto.item.ItemDto;
import com.example.springstore.domain.dto.item.ItemInfoDto;
import com.example.springstore.domain.dto.item.ItemUpdateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Review;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
/**
 * Mapper for item group
 *  @author tagir
 *  @since 15.01.2022
 */
@Mapper
public interface ItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemGroup", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Item fromCreateDto(ItemCreateDto source);

    @Mapping(target = "groupId", source = "itemGroup.id")
    ItemDto toDto(Item source);
    ItemInfoDto toInfoDto(Item source);

    default Page<ItemDto> listToDto(Page<Item> source){
        return source.map(this::toDto);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemGroup", ignore = true)
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "orders", ignore = true)
    Item fromUpdateDto(ItemUpdateDto source);

    @Mapping(target = "rating", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Item merge(@MappingTarget Item target, Item source);
}
