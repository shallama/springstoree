package com.example.springstore.service;
import com.example.springstore.domain.dto.item.ItemSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Rating;
import com.example.springstore.domain.entity.Review;
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
    Item updateRating(UUID itemId, Integer rate);
    Page<Item> getItemsList(ItemSearchRequest searchRequest, Pageable pageable);
}
