package com.github.config;

import com.google.common.collect.Maps;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2020/02/19 16:16
 * *****************
 * function:
 */
@Configuration
public class DataSourceConfig {
	
	private static final String MYSQL_JDBC_URL = "jdbc:mysql://%s/system?characterEncoding=UTF-8";
	
	/**
	 * 可以改为从配置中心获取
	 * key为数据库实例名称
	 * value为host:port
	 */
	private Map<String, String> datasource = Maps.newHashMap();
	
	@Bean
	public AbstractRoutingDataSource routingDataSource() {
		AbstractRoutingDataSource abstractRoutingDataSource = new AbstractRoutingDataSource() {
			@Override
			protected Object determineCurrentLookupKey() {
				return CustomerContextHolder.getCustomerType();
			}
		};
		abstractRoutingDataSource.setTargetDataSources(initDataSource());
		return abstractRoutingDataSource;
	}
	
	private Map<Object, Object> initDataSource() {
		Map<Object, Object> result = Maps.newHashMap();
		datasource.forEach((instance, info) -> {
			result.put(instance, getMySQLDataSource(info));
		});
		return result;
	}
	
	private HikariDataSource getMySQLDataSource(String hostPort) {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		hikariDataSource.setUsername("username");
		hikariDataSource.setPassword("password");
		hikariDataSource.setJdbcUrl(String.format(MYSQL_JDBC_URL, hostPort));
		return hikariDataSource;
	}
	
}
