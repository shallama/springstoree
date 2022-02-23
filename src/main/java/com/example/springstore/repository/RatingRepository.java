package com.example.springstore.repository;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
/**
 *  Repository for work with rating DB table
 *  @author tagir
 *  @since 15.01.2022
 */
@Repository
public interface RatingRepository extends JpaRepository<Rating, UUID> {
    Rating findByItem(Item item);
}
