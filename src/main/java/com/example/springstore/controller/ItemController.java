package com.example.springstore.controller;

import com.example.springstore.domain.dto.item.*;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.exeption.ItemNotFoundException;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
/**
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequestMapping(path = "items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final ItemMapper itemMapper;
    private final ReviewService reviewService;
    private final ReviewMapper reviewMapper;

    @GetMapping("/{itemId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ItemDto get(@PathVariable(name = "itemId") UUID itemId){
        return Optional.of(itemId)
                .map(itemService::get)
                .map(itemMapper::toDto)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    @GetMapping("/info/{itemId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ItemInfoDto getInfo(@PathVariable(name = "itemId") UUID id){
        return Optional.of(id)
                .map(itemService::get)
                .map(itemMapper::toInfoDto)
                .orElseThrow();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public ItemDto create(@Valid @RequestBody ItemCreateDto createDto){
        UUID groupId = createDto.getGroupId();
        return Optional.ofNullable(createDto)
                .map(itemMapper::fromCreateDto)
                .map(current -> itemService.create(groupId, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{itemId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public ItemDto update(@PathVariable (name = "itemId") UUID id,
                            @RequestBody ItemUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(itemMapper::fromUpdateDto)
                .map(current -> itemService.update(id, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{itemId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public void delete(@PathVariable (name = "itemId") UUID id){
        itemService.delete(id);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<ItemDto> getItemsList(@RequestBody ItemSearchRequest searchRequest,
                                      Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> itemService.getItemsList(curr, pageable))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

}
