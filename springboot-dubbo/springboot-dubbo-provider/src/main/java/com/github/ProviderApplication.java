package com.github;

import com.github.model.User;
import com.github.service.UserService;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@EnableDubbo
@RestController
@SpringBootApplication
@RequestMapping("/application")
public class ProviderApplication {

    @Resource
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ProviderApplication.class, args);
    }

    @GetMapping("/user")
    public User user() {
        return userService.getUserByName("hangs.zhang");
    }

}
