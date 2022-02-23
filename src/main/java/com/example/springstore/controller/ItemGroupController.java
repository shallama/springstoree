package com.example.springstore.controller;

import com.example.springstore.domain.dto.itemgroup.GroupSearchRequest;
import com.example.springstore.domain.dto.itemgroup.ItemGroupCreateDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupUpdateDto;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.exeption.ItemGroupNotFoundException;
import com.example.springstore.domain.mapper.ItemGroupMapper;
import com.example.springstore.service.ItemGroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
/**
 *  Controller for work with item group information
 *  @author tagir
 *  @since 15.01.2022
 */
@RestController
@RequestMapping(path = "item-groups")
@RequiredArgsConstructor
@Tag(name = "ItemGroup", description = "Controller for work with itemGroup")
@ApiResponse(responseCode = "500", description = "Internal error")
@ApiResponse(responseCode = "400", description = "Validation failed")
@ApiResponse(responseCode = "404", description = "ItemGroup not found")
@ApiResponse(responseCode = "401", description = "Unauthorized user")
public class ItemGroupController {
    /** Item group service injecting     */
    private final ItemGroupService groupService;
    /** Item group service injecting     */
    private final ItemGroupMapper groupMapper;

    /**
     * Return item group on Json format
     * @param id
     * @return item group on Json format
     */
    @GetMapping("/{groupId}")
    @Operation(description = "Find item group by id")
    @ApiResponse(responseCode = "200", description = "Item group was found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public ItemGroupDto get(@PathVariable(name = "groupId") UUID id){
        return Optional.of(id)
                .map(groupService::get)
                .map(groupMapper::toDto)
                .orElseThrow(() -> new ItemGroupNotFoundException(id));
    }

    /**
     * Create and return created item group
     * @param createDto
     * @return created item group on Json format
     */
    @PostMapping
    @Operation(description = "Create item group by id")
    @ApiResponse(responseCode = "200", description = "Item group was created")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public ItemGroupDto create(@Valid @RequestBody ItemGroupCreateDto createDto){
        return Optional.ofNullable(createDto)
                .map(groupMapper::fromCreateDto)
                .map(groupService::create)
                .map(groupMapper::toDto)
                .orElseThrow();
    }

    /**
     * Update information about item group
     * and return updated item group on Json format
     * @param id
     * @param updateDto
     * @return updated item group on Json format
     */
    @PatchMapping("/{groupId}")
    @Operation(description = "Update item group by id")
    @ApiResponse(responseCode = "200", description = "Item group was updated")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public ItemGroupDto update(@PathVariable(name = "groupId") UUID id,
                                @RequestBody ItemGroupUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(groupMapper::fromUpdateDto)
                .map(current -> groupService.update(id, current))
                .map(groupMapper::toDto)
                .orElseThrow();
    }

    /**
     * Delete item group
     * @param id
     */
    @DeleteMapping("/{groupId}")
    @Operation(description = "Delete item group by id")
    @ApiResponse(responseCode = "204", description = "Item group was deleted")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_SELLER')")
    public void delete(@PathVariable(name = "groupId") UUID id){
        groupService.delete(id);
    }

    /**
     *  Return pageable filtered item group list
     * @param searchRequest
     * @param pageable
     * @return pageable filtered item group list
     */
    @GetMapping
    @Operation(description = "Find pageable filtered itemGroup list")
    @ApiResponse(responseCode = "200", description = "ItemGroup list was found")
    @PreAuthorize("hasAuthority('ROLE_ADMIN') || hasAuthority('ROLE_CUSTOMER') || hasAuthority('ROLE_SELLER')")
    public Page<ItemGroupDto> getGroupsList(@RequestBody GroupSearchRequest searchRequest,
                                            Pageable pageable){
        return Optional.ofNullable(searchRequest)
                .map(curr -> groupService.getGroupsList(curr,pageable))
                .map(groupMapper::listToDto)
                .orElseThrow();
    }
}
