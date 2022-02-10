package com.example.springstore.controller;

import com.example.springstore.domain.dto.item.ItemCreateDto;
import com.example.springstore.domain.dto.item.ItemDto;
import com.example.springstore.domain.dto.item.ItemInfoDto;
import com.example.springstore.domain.dto.item.ItemUpdateDto;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.exeption.ItemNotFoundException;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(path = "items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable(name = "itemId") UUID itemId){
        return Optional.of(itemId)
                .map(itemService::get)
                .map(itemMapper::toDto)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    @GetMapping("/info/{itemId}")
    public ItemInfoDto getInfo(@PathVariable(name = "itemId") UUID id){
        return Optional.of(id)
                .map(itemService::get)
                .map(itemMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    public ItemDto create(@Valid @RequestBody ItemCreateDto createDto){
        UUID groupId = createDto.getGroupId();
        return Optional.ofNullable(createDto)
                .map(itemMapper::fromCreateDto)
                .map(current -> itemService.create(groupId, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{itemId}")
    public ItemDto update(@PathVariable (name = "itemId") UUID id,
                            @RequestBody ItemUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(itemMapper::fromUpdateDto)
                .map(current -> itemService.update(id, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{itemId}")
    public void delete(@PathVariable (name = "itemId") UUID id){
        itemService.delete(id);
    }

    @PostMapping("/{itemId}/reviews/{orderId}")
    public ReviewDto createReview(@Valid @PathVariable(name = "itemId") UUID itemId,
                                  @PathVariable(name = "orderId") UUID orderId,
                                  @RequestBody ReviewCreateDto createDto){
        return Optional.ofNullable(createDto)
                .map(reviewMapper::fromCreateDto)
                .map(current -> reviewService.create(itemId,
                                        createDto.getUserId(), orderId, current))
                .map(reviewMapper::toDto)
                .orElseThrow();
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsList(@PathVariable(name = "pageNum") Integer pageNum,
                                      @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.ofNullable(pageNum)
                .map(curr -> itemService.getItemsList(curr, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/group/{groupId}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByGroup(@PathVariable(name = "groupId") UUID groupId,
                                         @PathVariable(name = "pageNum") Integer pageNum,
                                         @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.of(groupId)
                .map(curr -> itemService.getItemsByGroup(groupId, pageNum,pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/group/{groupId}/{availability}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByGroupAndAvailability(@PathVariable(name = "groupId") UUID groupId,
                                         @PathVariable(name = "pageNum") Integer pageNum,
                                         @PathVariable(name = "pageSize") Integer pageSize,
                                         @PathVariable Boolean availability){
        return Optional.of(groupId)
                .map(curr -> itemService.getItemsByGroupAndAvailability(curr, availability, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/{itemId}/reviews/{pageNum}/{pageSize}")
    public Page<ReviewDto> getReviewsByItemId(@PathVariable(name = "itemId") UUID id,
                                              @PathVariable(name = "pageNum") Integer pageNum,
                                              @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.of(id)
                .map(curr -> reviewService.getReviewByItem(curr, pageNum, pageSize))
                .map(reviewMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/rate/{rateCount}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByRate(@PathVariable(name = "rateCount") Integer rate,
                                        @PathVariable(name = "pageNum") Integer pageNum,
                                        @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.ofNullable(rate)
                .map(curr -> itemService.getItemsByRate(curr, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/rate/{availability}/{rateCount}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByRateAndAvailability(@PathVariable(name = "rateCount") Integer rate,
                                        @PathVariable(name = "pageNum") Integer pageNum,
                                        @PathVariable(name = "pageSize") Integer pageSize,
                                        @PathVariable(name = "availability") Boolean availability){
        return Optional.ofNullable(rate)
                .map(curr -> itemService.getItemsByRateAndAvailability(availability, curr, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/rate/{groupId}/{availability}/{rateCount}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByRateAndAvailabilityAndGroup(@PathVariable(name = "rateCount") Integer rate,
                                        @PathVariable(name = "pageNum") Integer pageNum,
                                        @PathVariable(name = "pageSize") Integer pageSize,
                                        @PathVariable(name = "availability") Boolean availability,
                                        @PathVariable(name = "groupId") UUID groupId){
        return Optional.ofNullable(rate)
                .map(curr -> itemService.getItemsByRateAndAvailabilityAndGroup(groupId, availability,curr,
                        pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/price/{maxPrice}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByPrice(@PathVariable(name = "pageNum") Integer pageNum,
                                         @PathVariable(name = "pageSize") Integer pageSize,
                                         @PathVariable(name = "maxPrice") Integer maxPrice){
        return Optional.ofNullable(maxPrice)
                .map(curr -> itemService.getItemsByPriceAndAvailability(curr, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/price-rate/{maxPrice}/{rateCount}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByPriceAndRate(@PathVariable(name = "rateCount") Integer rate,
                                        @PathVariable(name = "pageNum") Integer pageNum,
                                        @PathVariable(name = "pageSize") Integer pageSize,
                                        @PathVariable(name = "maxPrice") Integer maxPrice){
        return Optional.ofNullable(rate)
                .map(curr -> itemService.getItemsByPriceAndRate(maxPrice, curr, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/price-group/{groupId}/{maxPrice}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByPriceAndGroup(@PathVariable(name = "pageNum") Integer pageNum,
                                                 @PathVariable(name = "pageSize") Integer pageSize,
                                                 @PathVariable(name = "maxPrice") Integer maxPrice,
                                                 @PathVariable(name = "groupId") UUID groupId){
        return Optional.ofNullable(maxPrice)
                .map(curr -> itemService.getItemsByPriceAndGroup(curr, groupId, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/price-rate-group/{rate}/{groupId}/{maxPrice}/{pageNum}/{pageSize}")
    public Page<ItemDto> getItemsByPriceAndRateAndGroup(@PathVariable(name = "pageNum") Integer pageNum,
                                                        @PathVariable(name = "rate") Integer rate,
                                                        @PathVariable(name = "pageSize") Integer pageSize,
                                                        @PathVariable(name = "maxPrice") Integer maxPrice,
                                                        @PathVariable(name = "groupId") UUID groupId){
        return Optional.ofNullable(maxPrice)
                .map(curr -> itemService.getItemsByPriceAndRateAndGroup(curr, rate, groupId, pageNum, pageSize))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }
}
