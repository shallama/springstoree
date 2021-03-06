package com.example.springstore.domain.mapper;

import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.dto.review.ReviewUpdateDto;
import com.example.springstore.domain.entity.Review;
import org.mapstruct.*;
import org.springframework.data.domain.Page;

import java.util.List;
/**
 *  Mapper for review
 *  @author tagir
 *  @since 15.01.2022
 */
@Mapper
public interface ReviewMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "user", ignore = true)
    public Review fromCreateDto(ReviewCreateDto source);

    @Mapping(target = "itemId", source = "item.id")
    @Mapping(target = "userId", source = "user.id")
    ReviewDto toDto(Review source);

    default Page<ReviewDto> listToDto(Page<Review> source){
        return source.map(this::toDto);
    }

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "item", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "reviewDate", ignore = true)
     Review fromUpdateDto(ReviewUpdateDto source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review merge(@MappingTarget Review target, Review source);
}
