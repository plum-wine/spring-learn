package com.github.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Data
@Component
@ConfigurationProperties(prefix = "boy")
public class ApplicationConfig {

    private String name;

    private String age;

    private String username;

    private String password;

}
