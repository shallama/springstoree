package com.example.springstore.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

import static javax.persistence.CascadeType.*;
/**
 *  Item entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Entity
@Getter
@Setter
@Table(name = "items")
public class Item extends BaseEntity {
    private String itemName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "groupId")
    private ItemGroup itemGroup;

    private Integer price;
    private String description;
    private Boolean availability;

    @Setter(AccessLevel.PRIVATE)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "item")
    private Rating rating;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "item",orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Review> reviews = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "item",orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Order> orders = new ArrayList<>();
}
