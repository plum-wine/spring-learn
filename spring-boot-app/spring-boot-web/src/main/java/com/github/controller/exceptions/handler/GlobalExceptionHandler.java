package com.github.controller.exceptions.handler;

import com.github.controller.domain.vo.BaseResult;
import com.github.controller.enums.ResultEnum;
import com.github.controller.exceptions.GlobalException;
import com.github.controller.exceptions.context.SnapshotContext;
import com.github.controller.utils.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.lang.invoke.MethodHandles;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 * 当发生异常之后，如果在本类中找不到@ExceptionHandler修饰的方法进行异常处理时
 * 就会寻找@ControllerAdvice修饰的类中的@ExceptionHandler修饰的方法进行异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult<Object> exceptionHandler(Exception ex, HttpServletRequest request) {
        Object snap = SnapshotContext.get();
        LOGGER.error("RequestURI:{}, Param:{}", request.getRequestURI(), JsonUtils.toJson(request.getParameterMap()), ex);

        // 如果快照数据存在，返回
        if (snap != null) {
            return BaseResult.success(snap);
        }
        // 自定义的感兴趣的异常
        if (ex instanceof GlobalException) {
            GlobalException globalException = (GlobalException) ex;
            return BaseResult.error(globalException.getCode(), globalException.getMessage());
            // 其他异常
        } else {
            return BaseResult.error(ResultEnum.SERVER_INNER_ERROR);
        }
    }

}
