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

import java.util.Optional;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final UserMapper userMapper;
    @Autowired
    private final AddressService addressService;

    @SneakyThrows
    @Override
    public User get(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(UUID id, User user) {
        return Optional.of(id)
                .map(this::get)
                .map(current -> userMapper.merge(current, user))
                .map(userRepository::save)
                .orElseThrow();
    }
    @Override
    public void delete(UUID id) {
        final User user = userRepository.findById(id).orElseThrow();
        if (user.getAddress() != null)
            addressService.delete(user.getAddress().getId());
        userRepository.delete(user);
    }

    @Override
    public Address assignAddress(UUID id, Address address) {
        User user = get(id);
        if (user.getAddress() == null){
            Address address1 = addressService.create(address);
            user.setAddress(address1);
            update(id, user);
            return address1;
        }
        Address oldAddress = user.getAddress();
        return addressService.update(oldAddress.getId(), address);
    }

    @Override
    public Address updateAddress(UUID userId, UUID addressId, Address address) {
        User user = get(userId);
        if (user.getAddress() == null){
            // because my AddressCreateDto and AddressUpdateDto are same
            return assignAddress(userId, address);
        }
        return addressService.update(addressId, address);
    }

    @Override
    public User deleteAddress(UUID userId, UUID addressId) {
        User user = get(userId);
        if (user.getAddress() != null) {
            user.setAddress(null);
            update(userId, user);
        }
        //I think we shouldn't delete address, because we use it in order
        //but we can delete from AddressController
        //addressService.delete(addressId);
        return user;
    }
}
