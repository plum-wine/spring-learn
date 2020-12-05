package com.github.controller.service;

import com.github.controller.domain.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author hangs.zhang
 * @date 2019/4/9.
 * *****************
 * function:
 */
@Service
public class FoobarService {

    public String hello() {
        return "hello";
    }

    public UserVO getUserVO() {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setEmail("foobar@foobar.com");
        userVO.setUsername("foobar");
        userVO.setPassword("foobar");
        return userVO;
    }

    public String test() {
        return "test";
    }

    public Integer id(Integer id) {
        return id;
    }

    public String name(String name) {
        return name;
    }

}
