package com.github.controller.service;

import com.github.controller.domain.vo.BaseResult;
import com.github.controller.utils.JsonUtils;
import com.google.common.base.Stopwatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @author hangs.zhang
 * @date 2019/4/9.
 * *****************
 * function:
 */
@Service
public class BenchmarkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Value("${benchmark.task.time}")
    private Long benchmarkTaskTime;

    @Async
    public ListenableFuture<BaseResult<String>> listenableFutureAsync(boolean isCpuTask) {
        return AsyncResult.forValue(mockTask("listenableFutureAsync", isCpuTask));
    }

    @Async
    public CompletableFuture<BaseResult<String>> completableFutureAsync(boolean isCpuTask) {
        return AsyncResult.forValue(mockTask("completableFutureAsync", isCpuTask)).completable();
    }

    public BaseResult<String> mockTask(String mark, boolean isCpuTask) {
        BaseResult<String> result;
        Stopwatch stopwatch = Stopwatch.createStarted();
        if (isCpuTask) {
            result = mockCpuTask(mark);
        } else {
            result = mockIOTask(mark);
        }
        LOGGER.info("{} Task Mark:{}, cost time:{}ms", isCpuTask ? "Cpu" : "IO", mark, stopwatch.elapsed(TimeUnit.MILLISECONDS));
        return result;
    }

    private BaseResult<String> mockIOTask(String mark) {
        try {
            TimeUnit.MILLISECONDS.sleep(benchmarkTaskTime);
        } catch (InterruptedException e) {
            // do nothing
            LOGGER.error("Mock IO Task Error", e);
        }
        return BaseResult.success(mark);
    }

    /**
     * 占同cpu 100ms
     * 该方法不会去压榨cpu, 只是会让线程计算benchmarkTaskTime的时间
     * 但是这个时间也算上了不被分配cpu的时间, 不是纯计算的时间
     * 可以用来模拟即耗用io, 也耗用cpu的任务
     */
    private BaseResult<String> mockIOAndCpuTask(String mark) {
        long startTime = System.currentTimeMillis();
        // 占同cpu benchmarkTaskTime ms
        while ((System.currentTimeMillis() - startTime) <= benchmarkTaskTime) {
            Map<String, String> map = new HashMap<>();
            map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            map.put(UUID.randomUUID().toString(), UUID.randomUUID().toString());
            // 欺骗编译器, 不输出到控制台
            LOGGER.debug(JsonUtils.toJson(map));
        }
        LOGGER.info("Cpu Task Mark:{}", mark);
        return BaseResult.success(mark);
    }

    /**
     * 一段纯计算的任务, 无论是否分配cpu时间, 计算量都固定
     * 在6c的Mac机器上, 单线程计算一次, 耗时18ms在左右
     */
    private BaseResult<String> mockCpuTask(String mark) {
        int num = 0;
        int firstCycle = 500;
        int secondCycle = firstCycle * 100;
        for (int i = 0; i < firstCycle; i++) {
            for (int j = 0; j < secondCycle; j++) {
                num += i * j;
                if (num > 10000) {
                    num += i;
                }
            }
        }
        // 欺骗编译器, 防止优化, 因为前面的代码计算的数据都没有被依赖
        LOGGER.debug("num:{}", num);
        return BaseResult.success(mark);
    }

}
