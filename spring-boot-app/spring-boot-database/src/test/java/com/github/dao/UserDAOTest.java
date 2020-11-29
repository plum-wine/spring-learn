package com.github.dao;

import com.github.SpringBootDatabaseApplicationTests;
import com.github.dao.user.UserDAO;
import com.github.entity.UserDO;
import com.github.enums.UserState;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.invoke.MethodHandles;

public class UserDAOTest extends SpringBootDatabaseApplicationTests {

    @Autowired
    private UserDAO userDAO;

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test
    public void selectUserByUid() {
        UserDO userDO = userDAO.selectUserByUid(1);
        Assert.assertEquals(userDO.getUserState(), UserState.ACTIVE);
    }

    @Test
    public void insert() {
        UserDO userDO = new UserDO();
        userDO.setUsername("database");
        userDO.setPassword("database");
        userDO.setUserState(UserState.BLOCK);
        int count = userDAO.insert(userDO);
        LOGGER.info("user id : {}", userDO.getUid());
        Assert.assertNotNull(userDO.getUid());
        Assert.assertEquals(1, count);
    }

}