package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.repository.ItemRepository;
import com.example.springstore.service.ItemGroupService;
import com.example.springstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
    @Autowired
    private final ItemRepository itemRepository;
    @Autowired
    private final ItemMapper itemMapper;
    @Autowired
    private final ItemGroupService groupService;

    @Override
    public Item get(UUID id) {
        return itemRepository.findById(id).orElseThrow();
    }

    @Override
    public Item create(UUID groupId, Item item) {
        ItemGroup customItemGroup = groupService.get(groupId);
        item.setItemGroup(customItemGroup);
        return itemRepository.save(item);
    }
    @Override
    public Item update(UUID id, Item item) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> itemMapper.merge(current, item))
                .map(itemRepository::save)
                .orElseThrow();
    }

    @Override
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public List<Item> getItemsByGroup(UUID groupId) {
        return itemRepository.findAllByItemGroup(groupService.get(groupId));
        /*List<Item> resItems = new ArrayList<>();
        List<Item> items = itemRepository.findAll();
        for(Item item : items){
            ItemGroup group = item.getItemGroup();
            if (group.getId().equals(groupId))
                resItems.add(item);
        }
        return resItems;*/
    }
}
