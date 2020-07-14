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
        BaseResult<T> BaseResult = new BaseResult<>();
        BaseResult.setCode(ResultEnum.SUCCESS.getCode());
        BaseResult.setMsg(ResultEnum.SUCCESS.getMsg());
        BaseResult.setData(obj);
        return BaseResult;
    }

    public static <T> BaseResult<T> success() {
        return success(null);
    }

    public static <T> BaseResult<T> error(Integer code, String msg) {
        BaseResult<T> BaseResult = new BaseResult<>();
        BaseResult.setCode(code);
        BaseResult.setMsg(msg);
        BaseResult.setData(null);
        return BaseResult;
    }

    public static <T> BaseResult<T> error(ResultEnum resultEnum) {
        BaseResult<T> BaseResult = new BaseResult<>();
        BaseResult.setCode(resultEnum.getCode());
        BaseResult.setMsg(resultEnum.getMsg());
        BaseResult.setData(null);
        return BaseResult;
    }

}
