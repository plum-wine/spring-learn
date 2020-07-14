package com.github.service;

import com.github.model.User;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author hangs.zhang
 * @date 2018/11/22
 * *****************
 * function:
 */
@Service(version = "${user.service.version}")
public class UserServiceImpl implements UserService {

    @Override
    public User getUserByName(String name) {
        User user = new User();
        user.setName(name);
        user.setId(101);
        user.setAge(23);
        return user;
    }

    @Override
    public boolean checkUserByName(String name) {
        return true;
    }

}
