package com.ness.library.library.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public BookController bookController(){
        return new BookController();
    }
}
