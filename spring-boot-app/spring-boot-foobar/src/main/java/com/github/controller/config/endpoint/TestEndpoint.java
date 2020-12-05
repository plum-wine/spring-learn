package com.github.controller.config.endpoint;

import com.google.common.collect.Maps;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;

import java.util.Map;

/**
 * @author hangs.zhang
 * @date 2019/4/11.
 * *****************
 * function: 自定义端点
 */
@Endpoint(id = "customize")
public class TestEndpoint {
	
	@ReadOperation
	public Map<String, String> test() {
		Map<String, String> map = Maps.newHashMap();
		map.put("name", "foobar");
		map.put("message", "foobar");
		return map;
	}
	
}
