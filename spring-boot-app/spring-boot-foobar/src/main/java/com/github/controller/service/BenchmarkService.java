package com.github.controller.service;

import com.github.controller.domain.vo.BaseResult;
import com.github.controller.domain.vo.UserVO;
import com.github.controller.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.lang.invoke.MethodHandles;
import java.util.Random;
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
        if (isCpuTask) {
            return mockCpuTask(mark);
        }
        return mockIOTask(mark);
    }

    private BaseResult<String> mockIOTask(String mark) {
        try {
            TimeUnit.MILLISECONDS.sleep(benchmarkTaskTime);
        } catch (InterruptedException e) {
            // do nothing
            LOGGER.error("Mock IO Task Error", e);
        }
        LOGGER.info("IO Task Mark:{}", mark);
        return BaseResult.success(mark);
    }

    /**
     * 占同cpu 100ms
     */
    private BaseResult<String> mockCpuTask(String mark) {
        long startTime = System.currentTimeMillis();
        // 占同cpu COST_TIME ms
        while ((System.currentTimeMillis() - startTime) <= benchmarkTaskTime) {
            UserVO userVO = new UserVO();
            userVO.setPassword(UUID.randomUUID().toString());
            userVO.setUsername(UUID.randomUUID().toString());
            userVO.setId(new Random().nextInt());
            // 欺骗编译器, 不输出到控制台
            LOGGER.debug(JsonUtils.toJson(userVO));
        }
        LOGGER.info("Cpu Task Mark:{}", mark);
        return BaseResult.success(mark);
    }

}
