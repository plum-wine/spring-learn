package com.github.web;

import com.github.entity.Request;
import com.github.entity.Response;
import com.github.service.HelloClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2018/12/5
 * *****************
 * function:
 */
@RefreshScope
@RestController
public class HelloController implements HelloClient {

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
            e.printStackTrace();
        }
        Response response = new Response();
        response.setCode(200);
        response.setMessage("success");
        return response;
    }

}
