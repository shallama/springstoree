package com.example.springstore.service;

import com.example.springstore.domain.entity.Address;
import org.springframework.stereotype.Service;

import java.util.UUID;
/**
 *  Service for work with user address
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface AddressService {
    /**
     * Return address information by address id
     * @param id address id
     * @return address information by address id
     */
    Address get(UUID id);

    /**
     * Create and return created address
     * @param address entity
     * @return created address
     */
    Address create(Address address);

    /**
     * Update and return address information
     * @param id address id
     * @param address object which contain new information
     * @return updated address
     */
    Address update(UUID id, Address address);

    /**
     * Delete address by id
     * @param id address id
     */
    void delete(UUID id);
}
