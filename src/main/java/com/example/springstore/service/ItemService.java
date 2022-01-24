package com.example.springstore.service;
import com.example.springstore.domain.entity.Item;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
@Service
public interface ItemService {
    Item get(UUID id);
    Item create(UUID groupId, Item item);
    Item update(UUID id, Item item);
    void delete(UUID id);
    List<Item> getItemsByGroup(UUID groupId);
}
