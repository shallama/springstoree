package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.exeption.ItemGroupNotFoundException;
import com.example.springstore.domain.mapper.ItemGroupMapper;
import com.example.springstore.repository.ItemGroupRepository;
import com.example.springstore.service.ItemGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemGroupImpl implements ItemGroupService {

    private final ItemGroupRepository itemGroupRepository;
    private final ItemGroupMapper itemGroupMapper;

    @Override
    public ItemGroup get(UUID id) {
        return itemGroupRepository.findById(id).orElseThrow(() -> new ItemGroupNotFoundException(id));
    }

    @Override
    @Transactional
    public ItemGroup create(ItemGroup itemGroup) {
        return itemGroupRepository.save(itemGroup);
    }

    @Override
    @Transactional
    public ItemGroup update(UUID id, ItemGroup itemGroup) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> itemGroupMapper.merge(current, itemGroup))
                .map(itemGroupRepository::save)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        itemGroupRepository.deleteById(id);
    }
}
