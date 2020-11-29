package com.github.controller.domain.vo;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Data
public class UserVO {

    public interface UserInfo {
    }

    @JsonView(UserInfo.class)
    private Integer id;

    @NotBlank(message = "用户名不能为空")
    @JsonView(UserInfo.class)
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;

    @JsonView(UserInfo.class)
    private String email;

}
