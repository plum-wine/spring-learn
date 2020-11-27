package com.github.web.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.web.enums.ResultEnum;
import lombok.Data;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Data
@JsonView(UserVO.UserInfo.class)
public class BaseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public static <T> BaseResult<T> success(T obj) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(ResultEnum.SUCCESS.getCode());
        baseResult.setMsg(ResultEnum.SUCCESS.getMsg());
        baseResult.setData(obj);
        return baseResult;
    }

    public static <T> BaseResult<T> success() {
        return success(null);
    }

    public static <T> BaseResult<T> error(Integer code, String msg) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(code);
        baseResult.setMsg(msg);
        baseResult.setData(null);
        return baseResult;
    }

    public static <T> BaseResult<T> error(ResultEnum resultEnum) {
        BaseResult<T> baseResult = new BaseResult<>();
        baseResult.setCode(resultEnum.getCode());
        baseResult.setMsg(resultEnum.getMsg());
        baseResult.setData(null);
        return baseResult;
    }

}
