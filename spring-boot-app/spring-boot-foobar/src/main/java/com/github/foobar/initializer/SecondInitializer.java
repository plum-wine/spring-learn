package com.github.foobar.initializer;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.lang.invoke.MethodHandles;
import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2020/07/12 22:17
 * *****************
 * function:
 */
@Order(1)
public class SecondInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        ConfigurableEnvironment environment = configurableApplicationContext.getEnvironment();
        Map<String, Object> map = Maps.newHashMap();
        map.put("key2", "value2");
        MapPropertySource propertySource = new MapPropertySource("secondInitializer", map);
        environment.getPropertySources().addLast(propertySource);
        LOGGER.info("run SecondInitializer");
    }

}
