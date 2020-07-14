package com.github.foobar.web;

import com.github.foobar.service.FoobarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author hangs.zhang
 * @date 2020/07/12 22:23
 * *****************
 * function:
 */
@RestController
@RequestMapping("/foobar")
public class FoobarController {

    @Resource
    private FoobarService foobarService;

    @GetMapping("/value")
    public String first(String key) {
        return foobarService.getValue(key);
    }

}
