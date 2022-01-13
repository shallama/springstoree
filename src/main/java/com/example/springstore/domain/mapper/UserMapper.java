package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.user.UserCreateDto;
import com.example.springstore.domain.dto.user.UserDto;
import com.example.springstore.domain.dto.user.UserInfoDto;
import com.example.springstore.domain.dto.user.UserUpdateDto;
import com.example.springstore.domain.entity.User;
import org.mapstruct.*;

@Mapper
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    User fromCreateDto(UserCreateDto source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", ignore = true)
    User fromUpdateDto(UserUpdateDto source);

    UserDto toDto(User source);

    UserInfoDto toInfoDto(User source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User merge(@MappingTarget User target, User source);
}
