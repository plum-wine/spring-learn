package com.github.entity;

import com.github.enums.UserState;
import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/11/21
 * *****************
 * function:
 */
@Data
public class UserDO {

    private Integer uid;

    private String username;

    private String password;

    private UserState userState;

    /**
     * 业务逻辑: 主页文章, 与user一一对应
     */
    private HomePageDO homePage;

}
