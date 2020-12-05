package com.github.controller.handler.interceptors;

import com.github.controller.annotations.AccessLimit;
import com.github.controller.enums.ResultEnum;
import com.github.controller.domain.vo.BaseResult;
import com.github.controller.utils.JsonUtils;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.invoke.MethodHandles;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author hangs.zhang
 * @date 2018/11/20
 * *****************
 * function:
 */
@Component
public class AccessInterceptor extends HandlerInterceptorAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final LoadingCache<String, Integer> LIMIT = CacheBuilder.newBuilder()
            .expireAfterAccess(5, TimeUnit.SECONDS)
            .build(new CacheLoader<String, Integer>() {
                @Override
                public Integer load(String key) throws Exception {
                    return 0;
                }
            });

    @Override
    public boolean preHandle(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                             @NonNull Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            AccessLimit accessLimit = handlerMethod.getMethodAnnotation(AccessLimit.class);
            if (Objects.isNull(accessLimit)) {
                return true;
            }
            int maxCount = accessLimit.maxCount();
            boolean needLogin = accessLimit.needLogin();
            int second = accessLimit.second();

            String key = request.getRequestURI();
            if (needLogin) {
                // TODO: 2018/11/20
            } else {
                // TODO: 2018/11/20
            }
            // TODO: 2018/11/20 对maxCount和second的处理
        }
        LOGGER.info("access preHandle return true");
        return true;
    }

    /**
     * 写回浏览器
     *
     * @param response
     * @param resultEnum
     * @throws IOException
     */
    private void render(HttpServletResponse response, ResultEnum resultEnum) throws IOException {
        PrintWriter out = response.getWriter();
        out.println(JsonUtils.toJson(BaseResult.error(resultEnum)));
        out.flush();
        out.close();
    }

}

