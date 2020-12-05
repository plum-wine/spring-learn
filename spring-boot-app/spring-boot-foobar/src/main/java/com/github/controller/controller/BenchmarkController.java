package com.github.controller.controller;

import com.github.controller.domain.vo.BaseResult;
import com.github.controller.service.BenchmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
 * Tomcat nio线程数量500, 1000
 * DeferredResult 线程池数量500, 1000
 * <p>
 * 从JMeter压测的结果来看, DeferredResult和tomcat自带的nio在设置同样线程数量的情况下性能差不多
 * Spring @Async注解就差很多了, 不知道是什么地方的问题
 * <p>
 * JMeter测试数据, 3000线程, 循环100次调用
 * ramp-up period为1s, 10s之内启动所有线程
 */
@RestController
@RequestMapping("/benchmark")
public class BenchmarkController {

    ExecutorService executors = Executors.newFixedThreadPool(500);

    @Autowired
    private BenchmarkService benchmarkService;

    @GetMapping("/sync")
    public BaseResult<String> sync(@RequestParam(required = false, defaultValue = "false") boolean isCpuTask) {
        return benchmarkService.mockTask("sync", isCpuTask);
    }

    @GetMapping("/deferredResultAsync")
    public DeferredResult<BaseResult<String>> deferredResultAsync(@RequestParam(required = false, defaultValue = "false") boolean isCpuTask) {
        DeferredResult<BaseResult<String>> deferredResult = new DeferredResult<>();
        executors.submit(() -> deferredResult.setResult(benchmarkService.mockTask("deferredResultAsync", isCpuTask)));
        return deferredResult;
    }

    @GetMapping("/listenableFutureAsync")
    public ListenableFuture<BaseResult<String>> listenableFutureAsync(@RequestParam(required = false, defaultValue = "false") boolean isCpuTask) {
        return benchmarkService.listenableFutureAsync(isCpuTask);
    }

    @GetMapping("/completableFutureAsync")
    public CompletableFuture<BaseResult<String>> completableFutureAsync(@RequestParam(required = false, defaultValue = "false") boolean isCpuTask) {
        return benchmarkService.completableFutureAsync(isCpuTask);
    }

}
