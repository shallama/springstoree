package com.example.springstore.domain.entity;

import com.example.springstore.domain.entity.enums.Role;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;


@Entity
@Getter
@Setter
public class User extends BaseEntity {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String pwd;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address address;
}
