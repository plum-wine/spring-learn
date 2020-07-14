package com.github.web;

import com.github.pojo.User;
import com.github.repo.UserRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author hangs.zhang
 * @date 2019/3/21
 * *****************
 * function:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private UserRepo userRepo;

    public UserController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/")
    public Flux<User> list() {
        return userRepo.findAll();
    }

}
