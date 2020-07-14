package com.github.service;

import com.github.model.User;

/**
 * @author hangs.zhang
 * @date 2020/04/06 22:21
 * *****************
 * function:
 */
public interface UserService {

    User getUserByName(String name);

    boolean checkUserByName(String name);

}
