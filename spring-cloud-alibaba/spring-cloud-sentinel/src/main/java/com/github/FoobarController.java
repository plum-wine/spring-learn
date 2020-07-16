package com.github;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2020/07/16 23:17
 * *****************
 * function:
 */
@RestController
public class FoobarController {

    @GetMapping("/hi")
    @SentinelResource(value = "hi")
    public String hi(@RequestParam String name) {
        return "hi:" + name;
    }

}
