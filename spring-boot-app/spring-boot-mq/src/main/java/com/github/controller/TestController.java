package com.github.controller;

import com.github.service.IndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2019/3/4.
 * *****************
 * function:
 */
@RestController
public class TestController {

    @Autowired
    private IndicatorService indicatorService;

    @GetMapping("/send")
    public String send(String topic, String data) {
        indicatorService.sendMessage(topic, data);
        return "ok";
    }

}
