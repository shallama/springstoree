package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.dto.review.ReviewUpdateDto;
import com.example.springstore.domain.entity.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Review fromCreateDto(ReviewCreateDto source);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "userId", source = "user.id")
    public ReviewDto toDto(Review source);

    //public ReviewDto toInfoDto(Review source);

    List<ReviewDto> listToDto(List<Review> source);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "reviewDate", ignore = true)
    public Review fromUpdateDto(ReviewUpdateDto source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review merge(@MappingTarget Review target, Review source);
}
