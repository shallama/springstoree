package com.example.springstore.repository;

import com.example.springstore.domain.entity.Item;
import com.example.springstore.domain.entity.Review;
import com.example.springstore.domain.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReviewRepository extends JpaRepository<Review, UUID> {
    Page<Review> findAllByItem(Item item, Pageable pageable);
    Page<Review> findAllByUser(User user, Pageable pageable);
    Page<Review> findAllByItemRate(Integer itemRate, Pageable pageable);
}
