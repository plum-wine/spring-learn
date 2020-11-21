package com.github.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EnvController {
	
	@Value("${env:default}")
	private String env;
	
	@Value("${server.tomcat.basedir}")
	private String basedir;
	
	@GetMapping("/env")
	public String env() {
		return env;
	}
	
	@GetMapping("/basedir")
	public String basedir() {
		return basedir;
	}
	
}
