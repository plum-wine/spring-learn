package com.github.web;

import com.github.config.CustomerContextHolder;
import com.github.entity.User;
import com.github.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hangs.zhang
 * @date 2018/11/22
 * *****************
 * function:
 */
@RestController
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/selectUserByUid")
	public User selectUserByUid(@RequestParam Integer uid) {
		return userDAO.selectUserByUid(uid);
	}
	
	@GetMapping("/selectUserByUidAndInstance")
	public User selectUserByUidAndInstance(@RequestParam Integer uid, @RequestParam String instance) {
		CustomerContextHolder.setCustomerType(instance);
		return userDAO.selectUserByUid(uid);
	}
	
}
