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
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    @Override
    public Address get(UUID id) {
        return addressRepository.findById(id).orElseThrow(() -> new AddressNotFoundException());
    }

    @Override
    @Transactional
    public Address create(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(UUID id, Address address) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> addressMapper.merge(current, address))
                .map(addressRepository::save)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        addressRepository.deleteById(id);
    }
}
