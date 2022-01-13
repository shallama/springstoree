package com.example.springstore.service;

import com.example.springstore.domain.entity.User;

import java.util.UUID;

public interface UserService {
    User get(UUID id);
    User create(User userJson);
    User update(UUID id, User userJson);
    void delete(UUID id);
}
