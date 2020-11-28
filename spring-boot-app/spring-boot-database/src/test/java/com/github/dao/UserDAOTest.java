package com.github.dao;

import com.github.SpringBootDatabaseApplicationTests;
import com.github.entity.UserDO;
import com.github.enums.UserState;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserDAOTest extends SpringBootDatabaseApplicationTests {

    @Autowired
    private UserDAO userDAO;

    @Test
    public void selectUserByUid() {
        UserDO userDO = userDAO.selectUserByUid(1);
        Assert.assertEquals(userDO.getUserState(), UserState.ACTIVE);
    }

}