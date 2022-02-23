package com.example.springstore.service.impl;

import com.example.springstore.domain.dto.itemgroup.GroupSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.exeption.ItemGroupNotFoundException;
import com.example.springstore.domain.mapper.ItemGroupMapper;
import com.example.springstore.repository.ItemGroupRepository;
import com.example.springstore.repository.ItemRepository;
import com.example.springstore.service.ItemGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.SpringVersion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
/**
 *  Item group service implementation
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemGroupServiceImpl implements ItemGroupService {

    private final ItemGroupRepository itemGroupRepository;
    private final ItemGroupMapper itemGroupMapper;
    private final ItemRepository itemRepository;

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

    private Boolean checkList(UUID groupId, List<UUID> list){
        for (UUID id : list){
            if(groupId.equals(id)){
                return true;
            }
        }
        return false;
    }

    private List<UUID> getGroupsWithItems(List<Item> items){
        List<UUID> groupIds = new ArrayList<>();
        for(Item item : items){
            if (!checkList(item.getItemGroup().getId(), groupIds)){
                groupIds.add(item.getItemGroup().getId());
            }
        }
        return groupIds;
    }

    public Page<ItemGroup> getGroupsByItemAvailability(Pageable pageable) {
        List<Item> items = itemRepository.findAllByAvailability(true);
        List<UUID> groups = getGroupsWithItems(items);
        Page<ItemGroup> result = itemGroupRepository.findByIdIn(groups, pageable);
        return result;
    }

    @Override
    public Page<ItemGroup> getGroupsList(GroupSearchRequest searchRequest, Pageable pageable) {
        if (searchRequest.getItemAvailability()){
            return getGroupsByItemAvailability(pageable);
        }
        return itemGroupRepository.findAll(pageable);
    }

}
