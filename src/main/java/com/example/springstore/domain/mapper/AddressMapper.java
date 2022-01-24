package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.address.AddressCreateDto;
import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.dto.address.AddressUpdateDto;
import com.example.springstore.domain.entity.Address;
import org.mapstruct.*;

@Mapper
public interface AddressMapper {
    @Mapping(target = "id", ignore = true)
    Address fromCreateDto(AddressCreateDto source);

    AddressDto toDto(Address source);

    @Mapping(target = "id", ignore = true)
    Address fromUpdateDto(AddressUpdateDto source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address merge(@MappingTarget Address target, Address source);

}
