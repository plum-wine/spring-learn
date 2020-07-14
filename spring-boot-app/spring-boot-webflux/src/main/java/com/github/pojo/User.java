package com.github.pojo;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author hangs.zhang
 * @date 2019/3/21
 * *****************
 * function:
 */
@Data
@Document(collection = "user")
public class User {

    @Id
    private String id;

    private String name;

    private Integer age;

    public User(String id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
