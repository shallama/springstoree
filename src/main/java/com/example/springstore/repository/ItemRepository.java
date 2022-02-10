package com.example.springstore.repository;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ItemRepository extends JpaRepository<Item, UUID> {
    Page<Item> findAllByItemGroup(ItemGroup group, Pageable pageable);
    Page<Item> findAllByItemGroupAndAvailability(ItemGroup group, Boolean availability, Pageable pageable);
    Page<Item> findByIdIn(List<UUID> ids, Pageable pageable);
    Page<Item> findByAvailabilityAndIdIn(Boolean availability, List<UUID> ids, Pageable pageable);
    Page<Item> findByItemGroupAndAvailabilityAndIdIn(ItemGroup itemGroup, Boolean availability,
                                                     List<UUID> ids, Pageable pageable);

    List<Item> findAllByAvailability(Boolean availability);
    List<Item> findByAvailabilityAndIdIn(Boolean availability, List<UUID> ids);
    List<Item> findByItemGroupAndAvailabilityAndIdIn(ItemGroup itemGroup, Boolean availability,
                                                     List<UUID> ids);
}
