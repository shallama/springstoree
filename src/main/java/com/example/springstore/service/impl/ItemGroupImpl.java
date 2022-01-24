package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.mapper.ItemGroupMapper;
import com.example.springstore.repository.ItemGroupRepository;
import com.example.springstore.service.ItemGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Primary
@RequiredArgsConstructor
public class ItemGroupImpl implements ItemGroupService {
    @Autowired
    private final ItemGroupRepository itemGroupRepository;
    @Autowired
    private final ItemGroupMapper itemGroupMapper;

    @Override
    public ItemGroup get(UUID id) {
        return itemGroupRepository.findById(id).orElseThrow();
    }

    @Override
    public ItemGroup create(ItemGroup itemGroup) {
        return itemGroupRepository.save(itemGroup);
    }

    @Override
    public ItemGroup update(UUID id, ItemGroup itemGroup) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> itemGroupMapper.merge(current, itemGroup))
                .map(itemGroupRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        itemGroupRepository.deleteById(id);
    }
}
