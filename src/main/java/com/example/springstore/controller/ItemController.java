package com.example.springstore.controller;

import com.example.springstore.domain.dto.item.ItemCreateDto;
import com.example.springstore.domain.dto.item.ItemDto;
import com.example.springstore.domain.dto.item.ItemInfoDto;
import com.example.springstore.domain.dto.item.ItemUpdateDto;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.exeption.ItemNotFoundException;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "items")
@RequiredArgsConstructor
public class ItemController {
    @Autowired
    private final ItemService itemService;
    @Autowired
    private final ItemMapper itemMapper;
    @Autowired
    private final ReviewService reviewService;
    @Autowired
    private final ReviewMapper reviewMapper;

    @SneakyThrows
    @GetMapping("/{itemId}")
    public ItemDto get(@PathVariable(name = "itemId") UUID itemId){
        return Optional.of(itemId)
                .map(itemService::get)
                .map(itemMapper::toDto)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    @SneakyThrows
    @GetMapping("/info/{itemId}")
    public ItemInfoDto getInfo(@PathVariable(name = "itemId") UUID id){
        return Optional.of(id)
                .map(itemService::get)
                .map(itemMapper::toInfoDto)
                .orElseThrow();
    }

    @SneakyThrows
    @GetMapping("/group/{groupId}")
    public List<ItemDto> getByGroup(@PathVariable(name = "groupId") UUID groupId){
        return Optional.of(groupId)
                .map(itemService::getItemsByGroup)
                .map(itemMapper::listToDto)
                .orElseThrow();
    }


    @SneakyThrows
    @PostMapping
    @ResponseStatus(value = OK)
    public ItemDto create(@Valid @RequestBody ItemCreateDto createDto){
        UUID groupId = createDto.getGroupId();
        return Optional.ofNullable(createDto)
                .map(itemMapper::fromCreateDto)
                .map(current -> itemService.create(groupId, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }
    @SneakyThrows
    @PatchMapping("/{itemId}")
    @ResponseStatus(value = OK)
    public ItemDto update(@PathVariable (name = "itemId") UUID id,
                            @RequestBody ItemUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(itemMapper::fromUpdateDto)
                .map(current -> itemService.update(id, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    @SneakyThrows
    @DeleteMapping("/{itemId}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable (name = "itemId") UUID id){
        itemService.delete(id);
    }

    @SneakyThrows
    @PostMapping("/{itemId}/reviews")
    public ReviewDto createReview(@Valid @PathVariable(name = "itemId") UUID itemId, @RequestBody ReviewCreateDto createDto){
        return Optional.ofNullable(createDto)
                .map(reviewMapper::fromCreateDto)
                .map(current -> reviewService.create(itemId,
                                        createDto.getUserId(), current))
                .map(reviewMapper::toDto)
                .orElseThrow();
    }

    @SneakyThrows
    @GetMapping("/{itemId}/reviews")
    public List<ReviewDto> getReviewsByItemId(@PathVariable(name = "itemId") UUID id){
        return Optional.of(id)
                .map(reviewService::getReviewByItem)
                .map(reviewMapper::listToDto)
                .orElseThrow();
    }
}
