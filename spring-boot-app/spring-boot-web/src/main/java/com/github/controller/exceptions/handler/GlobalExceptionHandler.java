package com.github.controller.exceptions.handler;

import com.github.controller.exceptions.context.SnapshotContext;
import com.github.controller.enums.ResultEnum;
import com.github.controller.exceptions.GlobalException;
import com.github.controller.domain.vo.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 * 当发生异常之后，如果在本类中找不到@ExceptionHandler修饰的方法进行异常处理时
 * 就会寻找@ControllerAdvice修饰的类中的@ExceptionHandler修饰的方法进行异常处理
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult<Object> exceptionHandler(Exception ex, HttpServletRequest request) {
        Object snap = SnapshotContext.get();
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 打印发生异常的请求参数
        parameterMap.forEach((key, value) -> log.error("error param {}:{}", key, value));
        // 打印请求的路径，可以根据这个路径去在监控中mark
        log.error("RequestURI {}", request.getRequestURI());
        // 打印异常堆栈
        log.error("exception", ex);

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

    private String list2String(List<String> list) {
        String prefix = "validate error : ";
        StringBuilder content = new StringBuilder();
        for (int i = 0, length = list.size(); i < length; i++) {
            String str = list.get(i);
            if (i == length - 1) {
                content.append(str);
            } else {
                content.append(str).append(", ");
            }
        }
        return prefix + content.toString();
    }

}
