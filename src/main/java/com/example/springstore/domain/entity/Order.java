package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Order extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;
    private Integer itemCount;
    private String orderDate;
    //status can be of three types: 1)preparation 2)readyToSend 3)sent
    private String orderStatus;
    private Boolean orderCompleteness;
    private Double orderAmount;
}
