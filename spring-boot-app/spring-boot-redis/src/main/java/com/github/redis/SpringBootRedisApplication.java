package com.github.redis;

import com.github.redis.entity.User;
import com.github.redis.utils.RedisUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@SpringBootApplication
@EnableRedisHttpSession
public class SpringBootRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootRedisApplication.class, args);
    }

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping("/redis/set")
    public String set(@RequestParam String param1, @RequestParam String param2) {
        Map<String, String> map = Maps.newHashMap();
        map.put("param1", param1);
        map.put("param2", param2);
        redisUtil.set("param", map);
        return "set redis success";
    }

    @GetMapping("/redis/get")
    public Object get() {
        Object param = redisUtil.get("param");
        return param;
    }

    @GetMapping("/redis/user/set")
    public String setUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");
        redisUtil.set("user", user);
        return "set user 2 redis success";
    }

    @GetMapping("/redis/user/get")
    public User getUser() {
        return (User) redisUtil.get("user");
    }

}
