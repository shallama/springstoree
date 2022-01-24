package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Item extends BaseEntity {
    private String itemName;
    @OneToOne
    @JoinColumn(name = "groupId")
    private ItemGroup itemGroup;
    private String price;
    private String description;
    private Boolean availability;
}
