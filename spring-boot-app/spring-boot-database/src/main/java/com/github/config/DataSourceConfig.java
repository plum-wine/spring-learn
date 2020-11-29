package com.github.config;

import com.github.constant.Constants;
import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2020/02/19 16:16
 * *****************
 * function:
 */
// @Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.url}")
    private String mysqlJdbcUrl;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;

    /**
     * 可以改为从配置中心获取
     * key为数据库实例名称
     * value为host:port
     */
    private final Map<String, String> datasource = Maps.newHashMap();

    @PostConstruct
    public void init() {
        datasource.put(Constants.DEFAULT_DATABASE_INSTANCE, "127.0.0.1:3306");
        datasource.put("instance2", "127.0.0.1:3307");
    }

    @Bean
    public AbstractRoutingDataSource routingDataSource() {
        AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
            @Override
            protected Object determineCurrentLookupKey() {
                return CustomerDatabaseContextHolder.getCustomerType();
            }
        };
        abstractRoutingDataSource.setTargetDataSources(initDataSource());
        return abstractRoutingDataSource;
    }

    private Map<Object, Object> initDataSource() {
        Map<Object, Object> result = Maps.newHashMap();
        datasource.forEach((instance, info) -> {
            result.put(instance, getDataSource(info));
        });
        return result;
    }

    private HikariDataSource getDataSource(String hostPort) {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setDriverClassName(driverClassName);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setJdbcUrl(String.format(mysqlJdbcUrl, hostPort));
        return hikariDataSource;
    }

}
