package com.github.service.fallback;

import com.github.entity.Request;
import com.github.entity.Response;
import com.github.service.HelloClient;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @author hangs.zhang
 * @date 2018/12/6
 * *****************
 * function:
 */
@Component
public class HelloClientFallback implements HelloClient {

    @PostConstruct
    public void init() {
        System.out.println("HelloClientFallback init");
    }

    @Override
    public String say() {
        return "this is helloClient fallback say method";
    }

    @Override
    public Response deal(Request request) {
        Response response = new Response();
        response.setCode(500);
        response.setMessage("this is helloClient fallback deal method");
        return response;
    }

}
