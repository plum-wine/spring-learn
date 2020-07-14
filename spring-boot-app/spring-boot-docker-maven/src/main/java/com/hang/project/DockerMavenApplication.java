package com.hang.project;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DockerMavenApplication {

    @Value("${tag}")
    private String tag;

    public static void main(String[] args) {
        SpringApplication.run(DockerMavenApplication.class, args);
    }

    @GetMapping("/hello")
    public String hello() {
        return "start docker";
    }

    @GetMapping("/tag")
    public String tag() {
        return tag;
    }

}
