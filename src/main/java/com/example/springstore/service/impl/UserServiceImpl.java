package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.Address;
import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.exeption.UserNotFoundException;
import com.example.springstore.domain.mapper.UserMapper;
import com.example.springstore.repository.UserRepository;
import com.example.springstore.service.AddressService;
import com.example.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;
/**
 *  User service implementation
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressService addressService;

    @Override
    public User get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    @Transactional
    public User create(User user) {

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User update(UUID id, User user) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> userMapper.merge(current, user))
                .map(userRepository::save)
                .orElseThrow();
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        final User user = userRepository.findById(id).orElseThrow();
        if (user.getAddress() != null){
            addressService.delete(user.getAddress().getId());
        }
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public Address createAddress(UUID id, Address address) {
        User user = get(id);
        Address createdAddress = addressService.create(address); //address was added to DB
        user.setAddress(createdAddress); //user append address
        update(id, user); //address was added to table of users in DB
        return createdAddress; // address was sent to controller
    }

    @Override
    @Transactional
    public Address updateAddress(UUID userId, Address address) {
        User user = get(userId);
        Address oldAddress = user.getAddress();
        if (oldAddress == null){
            return createAddress(userId, address);
        }
        return addressService.update(oldAddress.getId(), address);
    }

}
