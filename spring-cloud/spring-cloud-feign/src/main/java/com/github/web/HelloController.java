package com.github.web;

import com.github.entity.Request;
import com.github.entity.Response;
import com.github.service.HelloClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

/**
 * @author hangs.zhang
 * @date 2018/12/5
 * *****************
 * function:
 */
@RestController
public class HelloController implements HelloClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/hello")
    public String hello() {
        return "hello spring cloud";
    }

    @Override
    @HystrixCommand
    @GetMapping("/say")
    public String say() {
        throw new RuntimeException("say hello");
    }

    @Override
    @HystrixCommand
    @PostMapping("/deal")
    public Response deal(@RequestBody Request request) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            LOGGER.error("sleep error", e);
        }
        Response response = new Response();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

}
