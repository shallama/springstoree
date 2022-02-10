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
@Table(name = "orders")
public class Order extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    private Integer itemCount;
    private LocalDate orderDate;
    @Enumerated(EnumType.STRING)
    private Status orderStatus;
    private Boolean orderCompleteness;
    private Integer amount;
    private Boolean isReviewed;
}
