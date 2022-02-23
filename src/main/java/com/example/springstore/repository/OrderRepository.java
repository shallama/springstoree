package com.example.springstore.repository;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Order;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.entity.enums.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
/**
 *  Repository for work with order DB table
 *  @author tagir
 *  @since 15.01.2022
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order> {
}
