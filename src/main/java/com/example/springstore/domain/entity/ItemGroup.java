package com.example.springstore.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;
/**
 *  Item group entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Entity
@Getter
@Setter
@Table(name = "item_groups")
public class ItemGroup extends BaseEntity {
    private String groupName;
    private String description;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "itemGroup",
                cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Item> items = new ArrayList<>();
}
