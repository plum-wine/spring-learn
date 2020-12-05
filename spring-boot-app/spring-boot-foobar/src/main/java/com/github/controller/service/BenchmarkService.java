package com.github.controller.service;

import com.github.controller.domain.vo.BaseResult;
import com.github.controller.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.invoke.MethodHandles;
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

    private static final long SLEEP_TIME_300 = 300;

    @Async
    public ListenableFuture<BaseResult<String>> listenableFutureAsync(boolean isCpuTask) {
        return AsyncResult.forValue(mockTask("listenableFutureAsync", isCpuTask));
    }

    @Async
    public CompletableFuture<BaseResult<String>> completableFutureAsync(boolean isCpuTask) {
        return AsyncResult.forValue(mockTask("completableFutureAsync", isCpuTask)).completable();
    }

    public BaseResult<String> mockTask(String mark, boolean isCpuTask) {
        if (isCpuTask) {
            return mockCpuTask(mark);
        }
        return mockIOTask(mark);
    }

    public BaseResult<String> mockIOTask(String mark) {
        try {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME_300);
        } catch (InterruptedException e) {
            // do nothing
            LOGGER.error("mockTask error", e);
        }
        LOGGER.info("mark:{}", mark);
        return BaseResult.success(mark);
    }

    /**
     * 占同cpu 100ms
     */
    public BaseResult<String> mockCpuTask(String mark) {
        int busyTime = 100;
        long startTime = System.currentTimeMillis();
        // 占同cpu 100ms
        while ((System.currentTimeMillis() - startTime) <= busyTime) {
            JsonUtils.toJson(new Object());
        }

        LOGGER.info("mark:{}", mark);
        return BaseResult.success(mark);
    }

}
