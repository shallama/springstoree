package com.example.springstore.controller;

import com.example.springstore.domain.dto.itemgroup.ItemGroupCreateDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupDto;
import com.example.springstore.domain.dto.itemgroup.ItemGroupUpdateDto;
import com.example.springstore.domain.exeption.ItemGroupNotFoundException;
import com.example.springstore.domain.mapper.ItemGroupMapper;
import com.example.springstore.service.ItemGroupService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = "itemgroups")
@RequiredArgsConstructor
public class ItemGroupController {
    @Autowired
    private final ItemGroupService groupService;
    @Autowired
    private final ItemGroupMapper groupMapper;

    @SneakyThrows
    @GetMapping("/{groupId}")
    public ItemGroupDto get(@PathVariable(name = "groupId") UUID id){
        return Optional.of(id)
                .map(groupService::get)
                .map(groupMapper::toDto)
                .orElseThrow(() -> new ItemGroupNotFoundException(id));
    }

    @SneakyThrows
    @PostMapping
    @ResponseStatus(value = OK)
    public ItemGroupDto create(@Valid @RequestBody ItemGroupCreateDto createDto){
        return Optional.ofNullable(createDto)
                .map(groupMapper::fromCreateDto)
                .map(groupService::create)
                .map(groupMapper::toDto)
                .orElseThrow();
    }

    @SneakyThrows
    @PatchMapping("/{groupId}")
    @ResponseStatus(value = OK)
    public ItemGroupDto update(@PathVariable(name = "groupId") UUID id,
                                @RequestBody ItemGroupUpdateDto updateDto){
        return Optional.ofNullable(updateDto)
                .map(groupMapper::fromUpdateDto)
                .map(current -> groupService.update(id, current))
                .map(groupMapper::toDto)
                .orElseThrow();
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(value = NO_CONTENT)
    public void delete(@PathVariable(name = "groupId") UUID id){
        groupService.delete(id);
    }
}
