package com.example.springstore.service.impl;

import com.example.springstore.service.DateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
/**
 *  Data service implementation
 *  @author tagir
 *  @since 15.01.2022
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DateServiceImpl implements DateService {
    @Override
    public LocalDate get() {
        return LocalDate.now();
    }
}
