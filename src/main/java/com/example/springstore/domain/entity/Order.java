package com.example.springstore.domain.entity;

import com.example.springstore.domain.entity.enums.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
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
    private LocalDate orderDate;
    private Status orderStatus;
    private Boolean orderCompleteness;
    private Integer amount;
}
