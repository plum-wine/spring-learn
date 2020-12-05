package com.github.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class FoobarApplication {

    public static void main(String[] args) {
        SpringApplication.run(FoobarApplication.class, args);
    }

}
