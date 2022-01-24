package com.example.springstore.service;

import com.example.springstore.domain.entity.Address;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public interface AddressService {
    Address get(UUID id);
    Address create(Address address);
    Address update(UUID id, Address address);
    void delete(UUID id);
}
