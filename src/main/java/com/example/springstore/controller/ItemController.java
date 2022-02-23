package com.example.springstore.controller;

import com.example.springstore.domain.dto.item.*;
import com.example.springstore.domain.dto.review.ReviewCreateDto;
import com.example.springstore.domain.dto.review.ReviewDto;
import com.example.springstore.domain.exeption.ItemNotFoundException;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.domain.mapper.ReviewMapper;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;
/**
 *  Controller for work with item information
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequestMapping(path = "items")
@RequiredArgsConstructor
@Tag(name = "Item", description = "Controller for work with item")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "Item not found")
@ApiResponse(responseCode = "401", description = "Unauthorized user")
public class ItemController {

    /** Item service injecting     */
    private final ItemService itemService;
    /** Item mapper injecting     */
    private final ItemMapper itemMapper;
    /** Review service injecting     */
    private final ReviewService reviewService;
    /** Review mapper injecting     */
    private final ReviewMapper reviewMapper;

    /**
     * Return item by id on Json format
     * @param itemId
     * @return item on Json format
     */
    @GetMapping("/{itemId}")
    @Operation(description = "Find item by id")
    @ApiResponse(responseCode = "200", description = "Item found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ItemDto get(@PathVariable(name = "itemId") UUID itemId){
        return Optional.of(itemId)
                .map(itemService::get)
                .map(itemMapper::toDto)
                .orElseThrow(() -> new ItemNotFoundException(itemId));
    }

    /**
     * Return item with detailed information by id on Json format
     * @param id
     * @return item on Json format
     */
    @GetMapping("/info/{itemId}")
    @Operation(description = "Find item info by id")
    @ApiResponse(responseCode = "200", description = "Item info found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ItemInfoDto getInfo(@PathVariable(name = "itemId") UUID id){
        return Optional.of(id)
                .map(itemService::get)
                .map(itemMapper::toInfoDto)
                .orElseThrow();
    }

    /**
     * Get information about new item and return created item on Json format
     * @param createDto
     * @return created item
     */
    @PostMapping
    @Operation(description = "Create item")
    @ApiResponse(responseCode = "200", description = "Item created")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public ItemDto create(@Valid @RequestBody ItemCreateDto createDto){
        UUID groupId = createDto.getGroupId();
        return Optional.ofNullable(createDto)
                .map(itemMapper::fromCreateDto)
                .map(current -> itemService.create(groupId, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    /**
     * Get information for update item and return updated item on Json format
     * @param id
     * @param updateDto
     * @return updated item
     */
    @PatchMapping("/{itemId}")
    @Operation(description = "Update item by id")
    @ApiResponse(responseCode = "200", description = "Item updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public ItemDto update(@PathVariable (name = "itemId") UUID id,
                            @RequestBody ItemUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(itemMapper::fromUpdateDto)
                .map(current -> itemService.update(id, current))
                .map(itemMapper::toDto)
                .orElseThrow();
    }

    /**
     * Delete item by id
     * @param id
     */
    @DeleteMapping("/{itemId}")
    @Operation(description = "Delete item by id")
    @ApiResponse(responseCode = "204", description = "Item deleted")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public void delete(@PathVariable (name = "itemId") UUID id){
        itemService.delete(id);
    }

    /**
     * Return pageable filtered item list
     * @param searchRequest
     * @param pageable
     * @return pageable item list
     */
    @GetMapping
    @Operation(description = "Find pageable filtered item list")
    @ApiResponse(responseCode = "200", description = "Item list found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<ItemDto> getItemsList(@RequestBody ItemSearchRequest searchRequest,
                                      Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> itemService.getItemsList(curr, pageable))
                .map(itemMapper::listToDto)
                .orElseThrow();
    }

}
