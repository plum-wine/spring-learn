package com.github.web.config;

import com.github.web.config.endpoint.TestEndpoint;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author hangs.zhang
 * @date 2019/4/11.
 * *****************
 * function:
 */
@Configuration
public class EndpointConfiguration {

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnAvailableEndpoint
    public TestEndpoint testEndpoint() {
        return new TestEndpoint();
    }

}
