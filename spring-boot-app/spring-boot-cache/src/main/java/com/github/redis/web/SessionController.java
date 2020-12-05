package com.github.redis.web;

import com.google.common.collect.Maps;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2019/4/12.
 * *****************
 * function:
 */
@RestController
public class SessionController {

    @GetMapping("/session")
    public Map<String, Object> getSession(HttpServletRequest request) {
        Map<String, Object> map = Maps.newHashMap();
        request.getSession().setAttribute("username", "admin");
        map.put("sessionId", request.getSession().getId());
        return map;
    }

    @GetMapping("/get")
    public String get(HttpServletRequest request) {
        return (String) request.getSession().getAttribute("username");
    }

}
