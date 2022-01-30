package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.util.UUID;


@Entity
@Getter
@Setter
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
    private String pwd;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;
}
