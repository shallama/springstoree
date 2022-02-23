package com.example.springstore.repository;

import com.example.springstore.domain.entity.ItemGroup;
import com.example.springstore.domain.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
/**
 *  Repository for work with itemGroup DB table
 *  @author tagir
 *  @since 15.01.2022
 */
@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, UUID>, JpaSpecificationExecutor<ItemGroup> {
    Page<ItemGroup> findByIdIn(List<UUID> ids, Pageable pageable);
}
