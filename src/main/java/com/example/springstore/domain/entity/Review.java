package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Review extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "itemId")
    private Item item;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private LocalDate reviewDate;
    private String comment;
    private Integer itemRate;
}
