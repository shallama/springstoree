package com.example.springstore.domain.mapper;
import com.example.springstore.domain.dto.item.ItemCreateDto;
import com.example.springstore.domain.dto.item.ItemDto;
import com.example.springstore.domain.dto.item.ItemInfoDto;
import com.example.springstore.domain.dto.item.ItemUpdateDto;
import com.example.springstore.domain.entity.Item;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ItemMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemGroup", ignore = true)
    Item fromCreateDto(ItemCreateDto source);

    @Mapping(target = "groupId", source = "itemGroup.id")
    ItemDto toDto(Item source);
    ItemInfoDto toInfoDto(Item source);

    List<ItemDto> listToDto(List<Item> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "itemGroup", ignore = true)
    Item fromUpdateDto(ItemUpdateDto source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Item merge(@MappingTarget Item target, Item source);
}
