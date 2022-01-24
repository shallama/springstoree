package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class ItemGroup extends BaseEntity {
    private String groupName;
    private String description;
}
