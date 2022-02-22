package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "ratings")
public class Rating extends BaseEntity{
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;
    private Integer rateSum;
    private Integer rateCount;
}
