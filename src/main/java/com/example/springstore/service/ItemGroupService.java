package com.example.springstore.service;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface ItemGroupService {
    ItemGroup get(UUID id);
    ItemGroup create(ItemGroup itemGroup);
    ItemGroup update(UUID id, ItemGroup itemGroup);
    void delete(UUID id);
}
