package com.github.web;

import com.github.client.LocalHelloClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2018/12/6
 * *****************
 * function:
 */
@Slf4j
@RestController
public class SleuthController {

    @Autowired
    LocalHelloClient helloClient;

    // 声明式REST客户端，伪RPC的方式
    @GetMapping("/nodeTwo")
    public String sleuth1() {
        String nodeOne = helloClient.nodeOne();
        log.info("result:{}", nodeOne);
        return "nodeTwo";
    }

}
