package com.example.springstore.service;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public interface UserService {
    User get(UUID id);
    User create(User user);
    User update(UUID id, User user);
    void delete(UUID id);
    Address createAddress(UUID id, Address addressId);
    Address updateAddress(UUID userId, Address address);
}
