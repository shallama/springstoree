package com.example.springstore.unit;

import com.example.springstore.domain.dto.item.ItemSearchRequest;
import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.entity.Rating;
import com.example.springstore.domain.mapper.ItemMapper;
import com.example.springstore.repository.ItemRepository;
import com.example.springstore.service.ItemGroupService;
import com.example.springstore.service.RatingService;
import com.example.springstore.service.impl.ItemServiceImpl;
import com.sun.source.tree.ModuleTree;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;
/**
 * Tests for item service
 * @author tagir
 * @since 20.02.2022
 */
@ExtendWith(MockitoExtension.class)
public class ItemServiceImplTest {
    @InjectMocks
    private ItemServiceImpl itemServiceImpl;
    @Mock
    private ItemRepository itemRepository;
    @Mock
    private ItemMapper itemMapper;
    @Mock
    private ItemGroupService groupService;
    @Mock
    private RatingService ratingService;

    /**
     * When item was created
     */
    @Test
    public  void itemCreateService(){
        Item item = new Item();
        UUID groupId = UUID.randomUUID();
        ItemGroup itemGroup = new ItemGroup();
        Rating rating = new Rating();
        rating.setItem(item);
        Mockito.when(groupService.get(groupId)).thenReturn(itemGroup);
        Mockito.when(itemRepository.save(item)).thenReturn(item);
        Mockito.when(ratingService.create(rating)).thenReturn(rating);
        itemServiceImpl.create(groupId, item);
        Mockito.verify(ratingService, Mockito.times(1)).create(rating);
    }

    /**
     * When client want to get item list by different parameters
     */
    @Test
    public void getItemListWithAllParams(){
        Pageable pageable = PageRequest.of(0, 50);
        UUID groupId = UUID.randomUUID();
        ItemSearchRequest searchRequest = ItemSearchRequest.builder()
                .groupId(groupId)
                .maxPrice(2000)
                .availability(true)
                .rating(5)
                .build();
        itemServiceImpl.getItemsList(searchRequest, pageable);
    }

}
