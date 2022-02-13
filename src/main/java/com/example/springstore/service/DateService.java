package com.example.springstore.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface DateService {
    LocalDate get();
}
