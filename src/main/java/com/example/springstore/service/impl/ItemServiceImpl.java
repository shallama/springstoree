package com.example.springstore.service.impl;

import com.example.springstore.domain.dto.item.ItemSearchRequest;
import com.example.springstore.domain.entity.*;
import com.example.springstore.domain.exeption.ItemNotFoundException;
import com.example.springstore.domain.exeption.ItemWasNotSavedException;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.repository.ItemRepository;
import com.example.springstore.service.ItemGroupService;
import com.example.springstore.service.ItemService;
import com.example.springstore.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;
    private final ItemGroupService groupService;
    private final RatingService ratingService;

    @Override
    public Item get(UUID id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNotFoundException(id));
    }

    @Override
    @Transactional
    public Item create(UUID groupId, Item item) {
        ItemGroup itemGroup = groupService.get(groupId);
        item.setItemGroup(itemGroup);
        Item newItem = itemRepository.save(item);
        Rating rating = new Rating();
        rating.setItem(newItem);
        ratingService.create(rating);
        return newItem;
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
    public Item updateRating(UUID itemId, Integer rate) {
        Item item = get(itemId);
        Rating rating = ratingService.getByItem(item);
        Integer rateSum = rating.getRateSum();
        Integer rateCount = rating.getRateCount();
        if (rateCount == null || rateCount == null){
            rateCount = 0;
            rateSum = 0;
        }
        rating.setRateSum(rate + rateSum);
        rating.setRateCount(++rateCount);
        ratingService.update(rating);
        return item;
    }


    @Override
    public Page<Item> getItemsList(ItemSearchRequest searchRequest, Pageable pageable) {
        Specification<Item> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesList = new ArrayList<>();
            if (searchRequest.getGroupId() != null){
                Join<Item, ItemGroup> groupJoin = root.join("itemGroup");
                ItemGroup itemGroup = groupService.get(searchRequest.getGroupId());
                Predicate groupPredicate = criteriaBuilder.equal(root.get("itemGroup"), itemGroup);
                predicatesList.add(groupPredicate);
            }
            if (searchRequest.getMaxPrice() != null){
                Predicate pricePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("price"), searchRequest.getMaxPrice());
                predicatesList.add(pricePredicate);
            }
            if (searchRequest.getAvailability() != null){
                Predicate predicate = criteriaBuilder.equal(root.get("availability"), searchRequest.getAvailability());
                predicatesList.add(predicate);
            }
            if (searchRequest.getRating() != null){
                Join<Item, Rating> ratings = root.join("rating");
                Expression<Number> rating = criteriaBuilder.quot(ratings.get("rateSum"), ratings.get("rateCount"));
                Expression<Integer> resRating = criteriaBuilder.toInteger(rating);
                Predicate ratingPredicate = criteriaBuilder.equal(resRating, searchRequest.getRating());
                predicatesList.add(ratingPredicate);
            }
            return criteriaBuilder.and(predicatesList.toArray(new Predicate[predicatesList.size()]));
        };
        return itemRepository.findAll(specification, pageable);
    }
}
