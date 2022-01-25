package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.exeption.AddressNotFoundException;
import com.example.springstore.domain.mapper.AddressMapper;
import com.example.springstore.repository.AddressRepository;
import com.example.springstore.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Autowired
    private final AddressRepository addressRepository;
    @Autowired
    private final AddressMapper addressMapper;

    @SneakyThrows
    @Override
    public Address get(UUID id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException(id));
    }

    @SneakyThrows
    @Override
    public Address create(Address address) {
        return addressRepository.save(address);
    }


    @SneakyThrows
    @Override
    public Address update(UUID id, Address address) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> addressMapper.merge(current, address))
                .map(addressRepository::save)
                .orElseThrow();
    }

    @SneakyThrows
    @Override
    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}
