package com.github.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author hangs.zhang
 * @date 2020/04/06 22:21
 * *****************
 * function:
 */
@Data
public class User implements Serializable {

    private Integer id;

    private String name;

    private Integer age;

}
