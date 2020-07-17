package com.github;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;

/**
 * @author hangs.zhang
 * @date 2020/07/16 23:17
 * *****************
 * function:
 */
@RestController
public class FoobarController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/hi")
    @SentinelResource(value = "hi", blockHandler = "exceptionHandler", fallback = "fallback")
    public String hi(@RequestParam String name) {
        return "hi:" + name;
    }

    /**
     * 降级处理
     */
    public String fallback(String name) {
        return "服务降级,name=" + name;
    }

    /**
     * 异常处理
     */
    public String exceptionHandler(String name, BlockException ex) {
        LOGGER.error("error", ex);
        return "服务抛异常,name=" + name;
    }

}
