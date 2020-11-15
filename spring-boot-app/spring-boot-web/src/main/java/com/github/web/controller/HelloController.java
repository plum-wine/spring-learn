package com.github.web.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.web.annotations.AccessLimit;
import com.github.web.annotations.Session;
import com.github.web.aop.annotations.StatisticsTime;
import com.github.web.config.ApplicationConfig;
import com.github.web.context.SnapshotContext;
import com.github.web.enums.ResultEnum;
import com.github.web.exceptions.GlobalException;
import com.github.web.response.BaseResult;
import com.github.web.response.UserVO;
import com.github.web.service.CommonBiz;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Objects;
import java.util.concurrent.Semaphore;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Slf4j
@RestController
@PropertySource("classpath:config.properties")
public class HelloController {

    /**
     * 这个参数是pc的用户名
     */
    @Value("${user.name}")
    private String username;

    /**
     * 从配置文件中读取
     */
    @Value("${test}")
    private String test;

    private final ApplicationConfig applicationConfig;

    @Autowired
    private CommonBiz commonBiz;

    @Autowired
    public HelloController(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @ModelAttribute
    public void common(HttpServletRequest request) {
        log.info("在所有的方法执行前执行");
        log.info(request.getRequestURL().toString());
    }

    @GetMapping("/hello")
    public BaseResult<String> hello() {
        return BaseResult.success("hello springboot");
    }

    @StatisticsTime("prefix")
    @GetMapping("/statisticsTime")
    public BaseResult<String> StatisticsTime() {
        return BaseResult.success("StatisticsTime");
    }

    @GetMapping("/snapshot")
    public BaseResult<String> snapshot() {
        String data = "这是一份快照数据";
        SnapshotContext.set(data);
        throw new RuntimeException();
    }

    @GetMapping("/exception")
    public BaseResult<String> exception() {
        throw new GlobalException(ResultEnum.ACCESS_LIMIT);
    }

    @GetMapping("/config")
    public BaseResult<ApplicationConfig> config() {
        return BaseResult.success(applicationConfig);
    }

    /**
     * 通过JsonView注解控制显示的json
     *
     * @return BaseResult
     */
    @JsonView(UserVO.UserInfo.class)
    @GetMapping("/json")
    public BaseResult<UserVO> jsonView() {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setEmail("1078504296@qq.com");
        userVO.setUsername("梅子酒");
        userVO.setPassword("hangs.zhang");
        return BaseResult.success(userVO);
    }

    @AccessLimit
    @GetMapping("/access")
    public BaseResult<String> access() {
        return BaseResult.success("access");
    }

    /**
     * validation JSR-303规范
     *
     * @param user          请求参数
     * @param bindingResult 错误处理
     * @return BaseResult
     */
    @PostMapping("/user")
    public BaseResult<?> user(@Valid @ModelAttribute UserVO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResult.success(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        user.setId(1);
        log.info("user : {}", user);
        return BaseResult.success(user);
    }

    @GetMapping("/session")
    public BaseResult<UserVO> session(@Session UserVO userVO) {
        if (Objects.isNull(userVO)) {
            return BaseResult.error(-1, "user为null");
        } else {
            return BaseResult.success(userVO);
        }
    }

    @GetMapping("/username")
    public String username() {
        return username;
    }

    @GetMapping("/test")
    public String test() {
        return test;
    }

    @GetMapping("/biz")
    public String biz(@RequestParam String data) {
        return commonBiz.deal(data);
    }

}