package com.github.controller;

import com.github.config.CustomerDatabaseContextHolder;
import com.github.entity.UserDO;
import com.github.dao.user.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Set;

/**
 * @author hangs.zhang
 * @date 2018/11/22
 * *****************
 * function:
 */
@RestController
@RequestMapping("/database")
public class DatabaseController {

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @GetMapping("/selectUserByUid")
    public UserDO selectUserByUid(@RequestParam Integer uid) {
        return userDAO.selectUserByUid(uid);
    }

    @GetMapping("/selectUserByUidAndInstance")
    public UserDO selectUserByUidAndInstance(@RequestParam Integer uid, @RequestParam String instance) {
        CustomerDatabaseContextHolder.setCustomerType(instance);
        return userDAO.selectUserByUid(uid);
    }

    @GetMapping("/queryUsers")
    public List<UserDO> queryUsers(@ModelAttribute UserDO query) {
        return userDAO.selectUsers(query);
    }

    @GetMapping("/queryUsersByIds")
    public List<UserDO> queryUsersByIds(@RequestParam(required = false) Set<Integer> userIds) {
        return userDAO.selectUsersByIds(userIds);
    }

    @GetMapping("/queryUsersByIds2")
    public List<UserDO> queryUsersByIds2(@RequestParam(required = false) Set<Integer> userIds) {
        return userDAO.selectUsersByIds2(userIds);
    }

    @PostMapping("/update")
    public String update(@RequestBody UserDO userDO) {
        int count = userDAO.update(userDO);
        LOGGER.info("update count : {}", count);
        return "success";
    }

}
