package com.example.springstore.service;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.User;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 *  Service for work with user entity
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface UserService {
    /**
     * Return user information by id
     * @param id
     * @return user
     */
    User get(UUID id);

    /**
     * Create and return user
     * @param user
     * @return user
     */
    User create(User user);

    /**
     * Update and return user information
     * @param id
     * @param user
     * @return updated user
     */
    User update(UUID id, User user);

    /**
     * Delete user by id
     * @param id
     */
    void delete(UUID id);

    /**
     * Create address for user
     * @param id
     * @param addressId
     * @return created user address
     */
    Address createAddress(UUID id, Address addressId);

    /**
     * Update address for user
     * @param userId
     * @param address
     * @return updated user address
     */
    Address updateAddress(UUID userId, Address address);
}
