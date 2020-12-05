package com.github.controller.controller;

import com.github.controller.domain.vo.BaseResult;
import com.github.controller.service.FoobarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.CompletableFuture;
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

    /**
     * 1000的线程数量
     */
    ExecutorService executors = Executors.newFixedThreadPool(1000);

    @Autowired
    private FoobarService foobarService;

    @GetMapping("/sync")
    public BaseResult<String> sync() {
        return foobarService.mockTask("sync");
    }

    @GetMapping("/deferredResultAsync")
    public DeferredResult<BaseResult<String>> deferredResultAsync() {
        DeferredResult<BaseResult<String>> deferredResult = new DeferredResult<>();
        executors.submit(() -> deferredResult.setResult(foobarService.mockTask("deferredResultAsync")));
        return deferredResult;
    }

    @GetMapping("/async1")
    public ListenableFuture<BaseResult<String>> async1() {
        return foobarService.async1();
    }

    @GetMapping("/async2")
    public CompletableFuture<BaseResult<String>> async2() {
        return foobarService.async2();
    }

}
