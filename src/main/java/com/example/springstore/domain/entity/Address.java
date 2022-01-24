package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;
@Entity
@Getter
@Setter
public class Address extends BaseEntity {
    //private String city;
    private String street;
    private String houseNum;
    private String country;
    private String addressIndex;
}
