package com.example.springstore.service;

import com.example.springstore.domain.dto.itemgroup.GroupSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 *  Service for work with item group
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface ItemGroupService {
    /**
     * Return item group information by id
     * @param id item group
     * @return item group
     */
    ItemGroup get(UUID id);

    /**
     * Create and return created item group
     * @param itemGroup
     * @return created item group
     */
    ItemGroup create(ItemGroup itemGroup);

    /**
     * Update and return item group information
     * @param id item group id
     * @param itemGroup object which contain new information
     * @return updated item group
     */
    ItemGroup update(UUID id, ItemGroup itemGroup);

    /**
     * Delete item group by id
     * @param id
     */
    void delete(UUID id);

    /**
     * Return pageable item group list
     * @param searchRequest contains information whether to return a list of groups with available items or all groups that are
     * @param pageable information by page number, size and sorting
     * @return pageable of item group list
     */
    Page<ItemGroup> getGroupsList(GroupSearchRequest searchRequest, Pageable pageable);
}
