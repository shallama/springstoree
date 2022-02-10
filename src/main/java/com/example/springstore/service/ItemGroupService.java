package com.example.springstore.service;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface ItemGroupService {
    ItemGroup get(UUID id);
    ItemGroup create(ItemGroup itemGroup);
    ItemGroup update(UUID id, ItemGroup itemGroup);
    void delete(UUID id);
    Page<ItemGroup> getGroupsByItemAvailability(Boolean availability, Integer pageNum, Integer pageSize);
    Page<ItemGroup> getGroupsList(Integer pageNum, Integer pageSize);
}
