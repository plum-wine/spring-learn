package com.github.controller.handler;

import com.github.controller.annotations.Session;
import com.github.controller.domain.vo.UserVO;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * @author hangs.zhang
 * @date 2018/12/13
 * *****************
 * function: 参数解析器
 */
@Component
public class SessionResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getMethodAnnotation(Session.class) == null;
        // return methodParameter.getParameterType().equals(UserVO.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        UserVO userVO = new UserVO();
        String token = nativeWebRequest.getParameter("token");
        userVO.setEmail(token);
        userVO.setId(1);
        userVO.setUsername("foobar");
        userVO.setPassword("123456");
        return userVO;
    }
}
