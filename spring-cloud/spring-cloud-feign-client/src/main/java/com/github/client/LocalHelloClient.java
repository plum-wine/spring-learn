package com.github.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author hangs.zhang
 * @date 2018/12/5
 * *****************
 * function:
 */
@Component
@FeignClient(name = "PROVIDER-SERVICE")
public interface LocalHelloClient {

    @GetMapping("/nodeOne")
    String nodeOne();

}
