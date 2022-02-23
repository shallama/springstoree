package com.example.springstore.repository;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
/**
 *  Repository for work with item DB table
 *  @author tagir
 *  @since 15.01.2022
 */
@Repository
public interface ItemRepository extends JpaRepository<Item, UUID>, JpaSpecificationExecutor<Item> {
    List<Item> findAllByAvailability(Boolean availability);
}
