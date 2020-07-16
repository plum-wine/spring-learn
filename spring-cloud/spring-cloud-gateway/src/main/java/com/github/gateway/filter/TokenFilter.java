package com.github.gateway.filter;

import com.github.gateway.utils.JsonUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Objects;

/**
 * @author hangs.zhang
 * @date 2020/07/15 23:35
 * *****************
 * function:
 * GlobalFilter, 全局过滤器，不需要在配置文件中配置，作用在所有的路由上，
 * 最终通过GatewayFilterAdapter包装成GatewayFilterChain可识别的过滤器，
 * 它为请求业务以及路由的URI转换为真实业务服务的请求地址的核心过滤器，不需要配置，系统初始化时加载，并作用在每个路由上.
 */
@Order(1)
@Component
public class TokenFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        //获取url中的token
        MultiValueMap<String, String> requestMap = request.getQueryParams();
        String token = requestMap.getFirst("token");
        if (StringUtils.isEmpty(token)) {
            return this.returnData(-1, "非法请求", exchange);
        }
        //token无效则返回错误code和msg
        if (!Objects.equals("token", token)) {
            return this.returnData(-1, "无效的token", exchange);
        }
        return chain.filter(exchange);
    }

    private Mono<Void> returnData(Integer code, String msg, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("code", code);
        data.put("msg", msg);

        byte[] datas = JsonUtils.toJson(data).getBytes(StandardCharsets.UTF_8);
        DataBuffer buffer = response.bufferFactory().wrap(datas);
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

}
