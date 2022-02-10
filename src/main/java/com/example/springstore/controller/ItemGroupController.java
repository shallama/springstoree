package com.example.springstore.controller;

import com.example.springstore.domain.dto.itemgroup.ItemGroupCreateDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupUpdateDto;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.exeption.ItemGroupNotFoundException;
import com.example.springstore.domain.mapper.ItemGroupMapper;
import com.example.springstore.service.ItemGroupService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "item-groups")
@RequiredArgsConstructor
public class ItemGroupController {

    private final ItemGroupService groupService;
    private final ItemGroupMapper groupMapper;

    @GetMapping("/{groupId}")
    public ItemGroupDto get(@PathVariable(name = "groupId") UUID id){
        return Optional.of(id)
                .map(groupService::get)
                .map(groupMapper::toDto)
                .orElseThrow(() -> new ItemGroupNotFoundException(id));
    }

    @PostMapping
    public ItemGroupDto create(@Valid @RequestBody ItemGroupCreateDto createDto){
        return Optional.ofNullable(createDto)
                .map(groupMapper::fromCreateDto)
                .map(groupService::create)
                .map(groupMapper::toDto)
                .orElseThrow();
    }

    @PatchMapping("/{groupId}")
    public ItemGroupDto update(@PathVariable(name = "groupId") UUID id,
                                @RequestBody ItemGroupUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(groupMapper::fromUpdateDto)
                .map(current -> groupService.update(id, current))
                .map(groupMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{groupId}")
    public void delete(@PathVariable(name = "groupId") UUID id){
        groupService.delete(id);
    }

    @GetMapping("/{availability}/{pageNum}/{pageSize}")
    public Page<ItemGroupDto> getGroupByItemAvailability(@PathVariable(name = "availability") Boolean availability,
                                                         @PathVariable(name = "pageNum") Integer pageNum,
                                                         @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.ofNullable(availability)
                .map(curr -> groupService.getGroupsByItemAvailability(availability, pageNum, pageSize))
                .map(groupMapper::listToDto)
                .orElseThrow();
    }

    @GetMapping("/{pageNum}/{pageSize}")
    public Page<ItemGroupDto> getGroupsList(@PathVariable(name = "pageNum") Integer pageNum,
                                            @PathVariable(name = "pageSize") Integer pageSize){
        return Optional.ofNullable(pageNum)
                .map(curr -> groupService.getGroupsList(curr, pageSize))
                .map(groupMapper::listToDto)
                .orElseThrow();
    }
}
