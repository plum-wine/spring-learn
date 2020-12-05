package com.github.controller.service;

import com.github.controller.domain.vo.BaseResult;
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
public class FoobarService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final long SLEEP_TIME_300 = 300;

    @Async
    public ListenableFuture<BaseResult<String>> async1() {
        return AsyncResult.forValue(mockTask("@Async.async.ListenableFuture"));
    }

    @Async
    public CompletableFuture<BaseResult<String>> async2() {
        return AsyncResult.forValue(mockTask("@Async.async.CompletableFuture")).completable();
    }

    public BaseResult<String> mockTask(String mark) {
        try {
            TimeUnit.MILLISECONDS.sleep(SLEEP_TIME_300);
        } catch (InterruptedException e) {
            // do nothing
            LOGGER.error("mockTask error", e);
        }
        LOGGER.info("mark:{}", mark);
        return BaseResult.success(mark);
    }

}
