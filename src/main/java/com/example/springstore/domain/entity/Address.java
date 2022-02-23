package com.example.springstore.domain.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.UUID;
/**
 *  Address entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Entity
@Getter
@Setter
@Table(name = "addresses")
public class Address extends BaseEntity {
    private String city;
    private String street;
    private String houseNum;
    private String country;
    private String addressIndex;

    @Setter(AccessLevel.PRIVATE)
    @OneToOne(fetch = FetchType.LAZY, mappedBy = "address")
    private User user;
}
