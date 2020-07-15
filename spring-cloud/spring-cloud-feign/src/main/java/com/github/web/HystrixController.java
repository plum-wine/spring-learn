package com.github.web;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2018/12/6
 * *****************
 * function:
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback", commandProperties = {
        // 超时配置,以防止在配置文件中
        // @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500"),
        // 断路器配置
        // 设置开启断路器
        @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
        // 设置一个窗口内的请求数，当在该窗口内(即时间内)请求数达到了该值，则断路器会被打开
        @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
        // 设置在断路打开后，拒绝请求到再次尝试请求并决定断路器是否继续打开的时间
        @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000"),
        // 设置打开断路器并走回退逻辑的错误率
        @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60")
})
public class HystrixController {

    private static final String DEFAULT_FALLBACK = "default fallback";

    private static final String FALLBACK = "fallback";

    private static final String TEST = "test";

    @HystrixCommand(fallbackMethod = "fallback")
    @GetMapping("/message")
    public String message(@RequestParam String name) {
        if (FALLBACK.equals(name)) {
            throw new RuntimeException();
        }
        return "message";
    }

    @GetMapping("/test")
    @HystrixCommand(commandKey = "test")
    public String testDefaultProperties(@RequestParam(required = false, defaultValue = "test") String test) throws InterruptedException {
        if (TEST.equals(test)) {
            throw new RuntimeException("test");
        }
        Thread.sleep(600);
        return "this is test message";
    }

    public String fallback(@RequestParam String name) {
        return FALLBACK;
    }

    public String defaultFallback() {
        return DEFAULT_FALLBACK;
    }

}
