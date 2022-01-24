package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.item.ItemCreateDto;
import com.example.springstore.domain.dto.item.ItemDto;
import com.example.springstore.domain.dto.item.ItemInfoDto;
import com.example.springstore.domain.dto.item.ItemUpdateDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupCreateDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupUpdateDto;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import org.mapstruct.*;

@Mapper
public interface ItemGroupMapper {
    @Mapping(target = "id", ignore = true)
    ItemGroup fromCreateDto(ItemGroupCreateDto source);

    ItemGroupDto toDto(ItemGroup source);

    @Mapping(target = "id", ignore = true)
    ItemGroup fromUpdateDto(ItemGroupUpdateDto source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ItemGroup merge(@MappingTarget ItemGroup target, ItemGroup source);
}
