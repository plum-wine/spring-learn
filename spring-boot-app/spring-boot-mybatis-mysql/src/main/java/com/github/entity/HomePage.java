package com.github.entity;

import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/11/21
 * *****************
 * function: 主页
 */
@Data
public class HomePage {

    private Integer id;

    private String title;

    private Integer authorId;

    private String content;

}
