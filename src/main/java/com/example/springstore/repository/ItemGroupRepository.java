package com.example.springstore.repository;

import com.example.springstore.domain.entity.ItemGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ItemGroupRepository extends JpaRepository<ItemGroup, UUID> {
}
