package com.github.service;

import com.github.SpringBootKafkaApplicationTests;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author hangs.zhang
 * @date 2019/3/4.
 * *****************
 * function:
 */
public class IndicatorServiceTest extends SpringBootKafkaApplicationTests {

    @Autowired
    private IndicatorService indicatorService;

    @Test
    public void processMessage() {

    }

    @Test
    public void sendMessage() {
        indicatorService.sendMessage("topic1", "topic1-data");
    }
}