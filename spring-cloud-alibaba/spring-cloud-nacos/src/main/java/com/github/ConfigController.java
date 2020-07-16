package com.github;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2020/07/16 22:38
 * *****************
 * function:
 */
@RefreshScope
@RestController
public class ConfigController {

    @Value("${username:default}")
    private String username;

    @GetMapping("/username")
    public String username() {
        return username;
    }

}
