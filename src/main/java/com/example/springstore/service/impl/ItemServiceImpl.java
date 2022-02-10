package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.entity.Rating;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.exeption.ItemNotFoundException;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.repository.ItemRepository;
import com.example.springstore.repository.RatingRepository;
import com.example.springstore.repository.ReviewRepository;
import com.example.springstore.service.ItemGroupService;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemGroupService groupService;
    private final ReviewRepository reviewRepository;
    private final RatingRepository ratingRepository;

    @Override
    public Item get(UUID id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    @Transactional
    public Item create(UUID groupId, Item item) {
        ItemGroup customItemGroup = groupService.get(groupId);
        item.setItemGroup(customItemGroup);
        return itemRepository.save(item);
    }

    @Override
    @Transactional
    public Item update(UUID id, Item item) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> itemMapper.merge(current, item))
                .map(itemRepository::save)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        itemRepository.deleteById(id);
    }

    @Override
    public Page<Item> getItemsByGroupAndAvailability(UUID groupId, Boolean availability,
                                                     Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return itemRepository.findAllByItemGroupAndAvailability(groupService.get(groupId), availability, pageable);
    }

    @Override
    public Page<Item> getItemsList(Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return itemRepository.findAll(pageable);
    }

    @Override
    public Page<Item> getItemsByGroup(UUID groupId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        return itemRepository.findAllByItemGroup(groupService.get(groupId), pageable);
    }

    private Boolean checkList(Rating source, List<UUID> list){
        for (UUID id : list){
            if (source.getItem().getId().equals(id)){
                return true;
            }
        }
        return false;
    }

    private List<UUID> getIdsList(Integer rate, List<Rating> rating){
        List<UUID> itemIdListByRate = new ArrayList<>();
        double value;
        int counter;
        UUID itemId;
        for (Rating source : rating){
            itemId = source.getItem().getId();
            value = 0;
            counter = 0;
            if (!checkList(source, itemIdListByRate)){
                for (Rating source2 : rating){
                    if (source2.getItem().getId().equals(itemId)){
                        value = value + source2.getRate();
                        counter++;
                    }
                }
                if (Math.ceil(value / counter) == rate) {
                    itemIdListByRate.add(itemId);
                }
            }
        }
        return itemIdListByRate;
    }

    @Override
    public Page<Item> getItemsByRate(Integer rate, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Rating> rating = ratingRepository.findAll();
        List<UUID> itemIdListByRate = getIdsList(rate, rating);
        Page<Item> items = itemRepository.findByIdIn(itemIdListByRate, pageable);
        return items;
    }

    @Override
    public Page<Item> getItemsByRateAndAvailability(Boolean availability, Integer rate, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Rating> rating = ratingRepository.findAll();
        List<UUID> itemIdListByRate = getIdsList(rate, rating);
        Page<Item> items = itemRepository.findByAvailabilityAndIdIn(availability, itemIdListByRate, pageable);
        return items;
    }

    @Override
    public Page<Item> getItemsByRateAndAvailabilityAndGroup(UUID groupId, Boolean availability,
                                                            Integer rate, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Rating> rating = ratingRepository.findAll();
        List<UUID> itemIdListByRate = getIdsList(rate, rating);
        ItemGroup group = groupService.get(groupId);
        Page<Item> items = itemRepository.findByItemGroupAndAvailabilityAndIdIn(group, availability,
                itemIdListByRate, pageable);
        return items;
    }

    private List<UUID> getIdsListForPrice(Integer maxPrice){
        List<Item> allItems = itemRepository.findAll();
        List<UUID> ids = new ArrayList<>();
        for (Item item : allItems){
            if ((item.getPrice() <= maxPrice) && (item.getAvailability())){
                ids.add(item.getId());
            }
        }
        return ids;
    }

    @Override
    public Page<Item> getItemsByPriceAndAvailability(Integer maxPrice, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<UUID> itemIds = getIdsListForPrice(maxPrice);
        return itemRepository.findByIdIn(itemIds, pageable);
    }

    private List<UUID> getIdsListForPriceAndGroup(Integer maxPrice, UUID groupId){
        List<Item> allItems = itemRepository.findAll();
        List<UUID> ids = new ArrayList<>();
        for (Item item : allItems){
            if ((item.getPrice() <= maxPrice) && (item.getItemGroup().getId().equals(groupId))
                    && (item.getAvailability())){
                ids.add(item.getId());
            }
        }
        return ids;
    }

    @Override
    public Page<Item> getItemsByPriceAndGroup(Integer maxPrice, UUID groupId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<UUID> itemIds = getIdsListForPriceAndGroup(maxPrice, groupId);
        return itemRepository.findByIdIn(itemIds, pageable);
    }

    private List<UUID> getIdsByPriceAndRate(List<Item> items, List<UUID> priceIds){
        List<UUID> result = new ArrayList<>();
        for (Item item : items){
            for (UUID priceId : priceIds){
                if (item.getId().equals(priceId)){
                    result.add(priceId);
                    break;
                }
            }
        }
        return result;
    }

    @Override
    public Page<Item> getItemsByPriceAndRate(Integer maxPrice, Integer rate, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Rating> rating = ratingRepository.findAll();
        List<UUID> itemIdsByRate = getIdsList(rate, rating);
        List<Item> itemsByRateAndAvail = itemRepository.findByAvailabilityAndIdIn(true, itemIdsByRate);
        List<UUID> itemIdsByPrice = getIdsListForPrice(maxPrice);
        List<UUID> resultItemIds = getIdsByPriceAndRate(itemsByRateAndAvail, itemIdsByPrice);
        Page<Item> items = itemRepository.findByIdIn(resultItemIds, pageable);
        return items;
    }

    @Override
    public Page<Item> getItemsByPriceAndRateAndGroup(Integer maxPrice, Integer rate, UUID groupId, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        List<Rating> rating = ratingRepository.findAll();
        List<UUID> itemIdsByRate = getIdsList(rate, rating);
        ItemGroup group = groupService.get(groupId);
        List<Item> itemsByRateAndGroup = itemRepository.findByItemGroupAndAvailabilityAndIdIn(group,true, itemIdsByRate);
        List<UUID> itemIdsByPrice = getIdsListForPriceAndGroup(maxPrice, groupId);;
        List<UUID> resultItemIds = getIdsByPriceAndRate(itemsByRateAndGroup, itemIdsByPrice);
        Page<Item> items = itemRepository.findByIdIn(resultItemIds, pageable);
        return items;
    }
}
