package com.github.web.enums;

import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2017-12-28 20:09
 * *****************
 * function:
 */
@Getter
public enum ResultEnum {
    SUCCESS("success", 0),
    PARAM_ERROR("param error", -10005),
    SERVER_INNER_ERROR("server inner error", -10006),

    // login
    LOGIN_PASS_NULL("pass is null", -10001),
    LOGIN_VO_NULL("login vo is null", -10002),
    LOGIN_PASS_ERROR("pass error", -10003),
    LOGIN_MOBILE_NOT_EXIST("mobile not exist", -10004),

    // qos
    ACCESS_LIMIT("access qos", -10005);

    private String msg;

    private Integer code;

    ResultEnum(String msg, Integer code) {
        this.msg = msg;
        this.code = code;
    }

}
