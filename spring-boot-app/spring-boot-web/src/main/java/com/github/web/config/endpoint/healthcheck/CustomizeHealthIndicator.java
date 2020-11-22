package com.github.web.config.endpoint.healthcheck;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

/**
 * @author hangs.zhang
 * @date 2019/4/12.
 * *****************
 * function: 在/actuator/health接口中展示信息
 */
@Component("customizeHealthIndicator")
public class CustomizeHealthIndicator extends AbstractHealthIndicator {

    @Value("${code}")
    private int code;

    private static final String VERSION = "v1.0.1";

    @Override
    protected void doHealthCheck(Health.Builder builder) {
        if (code != 0) {
	        builder.down().withDetail("code", code).withDetail("version", VERSION).build();
        }
        builder.withDetail("code", code).withDetail("version", VERSION).up().build();
    }

}
