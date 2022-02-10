package com.example.springstore.repository;

import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, UUID> {
    Page<ItemGroup> findByIdIn(List<UUID> ids, Pageable pageable);
}
