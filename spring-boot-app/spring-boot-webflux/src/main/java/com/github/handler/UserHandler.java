package com.github.handler;

import com.github.pojo.User;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author hangs.zhang
 * @date 2019/3/23
 * *****************
 * function:
 */
@ComponentScan
public class UserHandler {

    public Mono<ServerResponse> getUser() {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .syncBody(new User("1", "hangs.zhang", 22));
    }

}
