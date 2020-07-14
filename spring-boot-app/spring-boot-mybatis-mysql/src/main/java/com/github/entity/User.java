package com.github.entity;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/11/21
 * *****************
 * function:
 */
@Data
public class User {

    private Integer uid;

    private String username;

    private String password;

    // 业务逻辑：主页文章，与user一一对应
    private HomePage article;

}
