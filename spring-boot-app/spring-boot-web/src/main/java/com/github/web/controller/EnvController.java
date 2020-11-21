package com.github.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {

    @Value("${env:default}")
    private String env;

    @GetMapping("/env")
    public String env() {
        return env;
    }

}
