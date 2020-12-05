package com.github.controller.controller;

import com.github.controller.domain.vo.BaseResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author zhanghang
 * @date 2020/12/5 10:21 上午
 * *****************
 * function:
 */
@RestController
@RequestMapping("/benchmark")
public class AsyncController {

    ExecutorService executors = Executors.newFixedThreadPool(200);

    /**
     * 3s
     */
    private static final long SLEEP_TIME_3000 = 3000;

    /**
     * 300ms
     */
    private static final long SLEEP_TIME_300 = 300;

    @GetMapping("/sync")
    public BaseResult<String> sync() {
        return mockTask("sync");
    }

    @GetMapping("/deferredResultAsync")
    public DeferredResult<BaseResult<String>> deferredResultAsync() {
        DeferredResult<BaseResult<String>> deferredResult = new DeferredResult<>();
        executors.submit(() -> deferredResult.setResult(mockTask("deferredResultAsync")));
        return deferredResult;
    }

    public BaseResult<String> mockTask(String mark) {
        try {
            Thread.sleep(SLEEP_TIME_300);
        } catch (InterruptedException e) {
            // do nothing
        }
        return BaseResult.success(mark);
    }

}
