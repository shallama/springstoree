package com.example.springstore.repository;

import com.example.springstore.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
/**
 *  Repository for work with address DB table
 *  @author tagir
 *  @since 15.01.2022
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, UUID> {
}
