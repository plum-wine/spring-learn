package com.github.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
	
	private Logger LOGGER = LoggerFactory.getLogger(EnvController.class);
	
	@Value("${env:default}")
	private String env;
	
	@Value("${server.tomcat.basedir}")
	private String basedir;
	
	@GetMapping("/env")
	public String env() {
		LOGGER.info("env:{}", env);
		return env;
	}
	
	@GetMapping("/basedir")
	public String basedir() {
		LOGGER.info("basedir:{}", basedir);
		return basedir;
	}
	
}
