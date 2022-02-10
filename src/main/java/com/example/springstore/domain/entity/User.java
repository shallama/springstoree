package com.example.springstore.domain.entity;

import com.example.springstore.domain.entity.enums.Role;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static javax.persistence.CascadeType.*;
import static javax.persistence.CascadeType.REFRESH;


@Entity
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String pwd;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "addressId")
    private Address address;

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user",orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Review> reviews = new ArrayList<>();

    @Setter(AccessLevel.PRIVATE)
    @OneToMany(mappedBy = "user",orphanRemoval = true,
            cascade = {PERSIST, MERGE, DETACH, REFRESH})
    private List<Order> orders = new ArrayList<>();
}
