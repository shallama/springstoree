package com.example.springstore.service.impl;

import com.example.springstore.domain.entity.User;
import com.example.springstore.domain.exeption.UserNotFoundException;
import com.example.springstore.domain.mapper.UserMapper;
import com.example.springstore.repository.UserRepository;
import com.example.springstore.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;
@Service
@Primary
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserMapper userMapper;

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
        /*final User user = userRepository.findById(id).orElseThrow();
        userRepository.delete(user);*/
        userRepository.deleteById(id);
    }
}
