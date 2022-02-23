package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.domain.dto.user.UserDto;
import com.example.springstore.domain.dto.user.UserInfoDto;
import com.example.springstore.domain.dto.user.UserUpdateDto;
import com.example.springstore.domain.entity.User;
import org.mapstruct.*;
import org.springframework.stereotype.Component;
/**
 *  Mapper for user
 *  @author tagir
 *  @since 15.01.2022
 */
@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User fromCreateDto(UserCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "address", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "orders", ignore = true)
    User fromUpdateDto(UserUpdateDto source);

    @Mapping(target = "addressId", source = "address.id")
    UserDto toDto(User source);

    UserInfoDto toInfoDto(User source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User merge(@MappingTarget User target, User source);
}
