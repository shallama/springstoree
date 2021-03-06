package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
/**
 *  Review entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Entity
@Getter
@Setter
@Table(name = "reviews")
public class Review extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "itemId")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")

    private User user;
    private LocalDate reviewDate;
    private String comment;
    private Integer itemRate;
}
