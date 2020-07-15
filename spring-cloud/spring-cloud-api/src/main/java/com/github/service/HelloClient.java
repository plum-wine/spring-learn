package com.github.service;

import com.github.entity.Request;
import com.github.entity.Response;
import com.github.service.fallback.HelloClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author hangs.zhang
 * @date 2018/12/4
 * *****************
 * function: fallback代表失败之后回调
 */
@FeignClient(name = "PROVIDER-SERVICE", fallback = HelloClientFallback.class)
public interface HelloClient {

    @GetMapping("/say")
    String say();

    @PostMapping("/deal")
    Response deal(@RequestBody Request request);

}
