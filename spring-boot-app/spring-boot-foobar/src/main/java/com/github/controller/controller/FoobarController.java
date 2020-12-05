package com.github.controller.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.controller.annotations.AccessLimit;
import com.github.controller.annotations.Session;
import com.github.controller.aop.annotations.StatisticsTime;
import com.github.controller.domain.vo.BaseResult;
import com.github.controller.domain.vo.UserVO;
import com.github.controller.enums.ResultEnum;
import com.github.controller.exceptions.GlobalException;
import com.github.controller.exceptions.context.SnapshotContext;
import com.github.controller.service.FoobarBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.lang.invoke.MethodHandles;
import java.util.Objects;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@RestController
public class FoobarController {

    @Autowired
    private FoobarBiz foobarBiz;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ModelAttribute
    public void before(HttpServletRequest request) {
        LOGGER.info("execute before all method");
        LOGGER.info("request url {}", request.getRequestURL());
    }

    @StatisticsTime("prefix")
    @GetMapping("/statisticsTime")
    public BaseResult<String> statisticsTime() {
        return BaseResult.success("StatisticsTime");
    }

    @GetMapping("/snapshot")
    public BaseResult<String> snapshot(@RequestParam(required = false, defaultValue = "true") boolean isUseSnapshot) {
        String data = "这是一份快照数据";
        SnapshotContext.set(data);
        if (isUseSnapshot) {
            throw new GlobalException("UseSnapshot");
        }
        return BaseResult.success();
    }

    @GetMapping("/exception")
    public BaseResult<String> exception() {
        throw new GlobalException(ResultEnum.ACCESS_LIMIT);
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
    public BaseResult<Object> user(@Valid @ModelAttribute UserVO user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return BaseResult.success(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        user.setId(1);
        LOGGER.info("user:{}", user);
        return BaseResult.success(user);
    }

    /**
     * 自定义session注解
     */
    @GetMapping("/session")
    public BaseResult<UserVO> session(@Session UserVO userVO) {
        if (Objects.isNull(userVO)) {
            return BaseResult.error(-1, "user为null");
        } else {
            return BaseResult.success(userVO);
        }
    }

    @GetMapping("/biz")
    public String biz(@RequestParam String data) {
        return foobarBiz.bizMethod(data);
    }

}