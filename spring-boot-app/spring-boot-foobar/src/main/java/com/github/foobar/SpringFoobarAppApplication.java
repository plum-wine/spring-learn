package com.github.foobar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringFoobarAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringFoobarAppApplication.class);
//        SpringApplication springApplication = new SpringApplication(SpringFoobarAppApplication.class);
//        springApplication.addInitializers(new SecondInitializer());
//        springApplication.run(args);
    }

}
