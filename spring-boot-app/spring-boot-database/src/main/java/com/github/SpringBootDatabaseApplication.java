package com.github;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringBootDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDatabaseApplication.class, args);
    }

}
