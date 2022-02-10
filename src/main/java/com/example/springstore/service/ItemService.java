package com.example.springstore.service;
import com.example.springstore.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ItemService {
    Item get(UUID id);
    Item create(UUID groupId, Item item);
    Item update(UUID id, Item item);
    void delete(UUID id);
    Page<Item> getItemsByGroupAndAvailability(UUID groupId, Boolean availability, Integer pageNum, Integer pageSize);

    Page<Item> getItemsList(Integer pageNum,Integer pageSize);

    Page<Item> getItemsByGroup(UUID groupId, Integer pageNum,Integer pageSize);

    Page<Item> getItemsByRate(Integer rate, Integer pageNum, Integer pageSize);

    Page<Item> getItemsByRateAndAvailability(Boolean availability, Integer rate, Integer pageNum, Integer pageSize);

    Page<Item> getItemsByRateAndAvailabilityAndGroup(UUID groupId, Boolean availability, Integer rate, Integer pageNum, Integer pageSize);

    Page<Item> getItemsByPriceAndAvailability(Integer maxPrice, Integer pageNum, Integer pageSize);

    Page<Item> getItemsByPriceAndGroup(Integer maxPrice, UUID groupId, Integer pageNum, Integer pageSize);

    Page<Item> getItemsByPriceAndRate(Integer maxPrice, Integer rate, Integer pageNum, Integer pageSize);

    Page<Item> getItemsByPriceAndRateAndGroup(Integer maxPrice, Integer rate, UUID groupId,
                                              Integer pageNum, Integer pageSize);
}
