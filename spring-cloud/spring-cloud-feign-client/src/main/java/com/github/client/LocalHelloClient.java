package com.github.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author hangs.zhang
 * @date 2018/12/5
 * *****************
 * function:
 */
@Component
@FeignClient(name = "FEIGN-SERVICE")
public interface LocalHelloClient {

    @GetMapping("/message")
    String message(@RequestParam String name);

}
