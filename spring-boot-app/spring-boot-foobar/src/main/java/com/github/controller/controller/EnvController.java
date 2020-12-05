package com.github.controller.controller;

import com.github.controller.config.ApplicationConfig;
import com.github.controller.domain.vo.BaseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/env")
@PropertySource("classpath:config.properties")
public class EnvController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${profile:spring.profiles.active}")
    private String profile;

    /**
     * 读取非application.properties的配置文件
     */
    @Value("${config.key}")
    private String config;

    /**
     * 这个参数是pc的用户名
     */
    @Value("${user.name}")
    private String username;

    @Autowired
    private ApplicationConfig applicationConfig;

    @GetMapping("/profile")
    public String env() {
        LOGGER.info("profile:{}", profile);
        return profile;
    }

    @GetMapping("/applicationConfig")
    public BaseResult<ApplicationConfig> applicationConfig() {
        return BaseResult.success(applicationConfig);
    }

    @GetMapping("/config")
    public String config() {
        return config;
    }

    /**
     * 读取环境变量
     */
    @GetMapping("/username")
    public String username() {
        return username;
    }


}
