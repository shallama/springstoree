package com.example.springstore.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String role;
}
