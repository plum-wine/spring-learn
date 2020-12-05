package com.github.controller.exceptions;

import com.github.controller.enums.ResultEnum;
import lombok.Getter;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Getter
public class GlobalException extends RuntimeException {

    private Integer code;

    public GlobalException(ResultEnum resultEnum) {
        this(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public GlobalException(String message) {
        super(message);
    }

    public GlobalException() {
        super();
    }

    public GlobalException(String message, Throwable cause) {
        super(message, cause);
    }

}
