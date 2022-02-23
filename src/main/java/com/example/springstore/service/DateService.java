package com.example.springstore.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
/**
 *  Service for work with date
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
public interface DateService {
    /**
     * Return current date
     * @return current date
     */
    LocalDate get();
}
