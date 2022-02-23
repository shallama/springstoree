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
/**
 *  Service for work with items
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface ItemService {
    /**
     * Return item information by id
     * @param id
     * @return item information
     */
    Item get(UUID id);

    /**
     * Create and return created item
     * Also creates a rating entity for accounting and
     * further calculation of the item rating
     * @param groupId for contain itemGroup to item
     * @param item
     * @return created item
     */
    Item create(UUID groupId, Item item);

    /**
     * Update and return item information
     * @param id
     * @param item object which contain new information
     * @return item information
     */
    Item update(UUID id, Item item);

    /**
     * Delete item by id
     * @param id
     */
    void delete(UUID id);

    /**
     * Update rating entity.
     * Called when user add review to item
     * @param itemId
     * @param rate which user left
     * @return item for further use
     */
    Item updateRating(UUID itemId, Integer rate);

    /**
     * Return the pageable filtered item list
     * @param searchRequest contain parameters for use filter
     * @param pageable information by page number, size and sorting
     * @return filtered pageable of item list
     */
    Page<Item> getItemsList(ItemSearchRequest searchRequest, Pageable pageable);
}
