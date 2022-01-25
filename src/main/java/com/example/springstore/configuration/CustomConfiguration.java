package com.example.springstore.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;

@Configuration
public class CustomConfiguration {
    @Bean
    public SimpleDateFormat dateFormat(){
        return new SimpleDateFormat("HH:mm dd.MM.yyyy");
    }
}
