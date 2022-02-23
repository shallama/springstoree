package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.address.AddressCreateDto;
import com.example.springstore.domain.dto.address.AddressDto;
import com.example.springstore.domain.dto.address.AddressUpdateDto;
import com.example.springstore.domain.entity.Address;
import org.mapstruct.*;
/**
 *  Mapper for address
 *  @author tagir
 *  @since 15.01.2022
 */
@Mapper
public interface AddressMapper {
    /**
     * Mapping addressCreateDto to address
     * @param source AddressCreateDto
     * @return address
     */
    @Mapping(target = "id", ignore = true)
    Address fromCreateDto(AddressCreateDto source);

    /**
     * Mapping Address to AddressDto
     * @param source address
     * @return addressDto
     */
    AddressDto toDto(Address source);

    /**
     *  Mapping from addressUpdateDto to Address
     * @param source AddressUpdateDto
     * @return address
     */
    @Mapping(target = "id", ignore = true)
    Address fromUpdateDto(AddressUpdateDto source);

    /**
     * Merge current address with new address
     * @param target
     * @param source
     * @return
     */
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address merge(@MappingTarget Address target, Address source);

}
