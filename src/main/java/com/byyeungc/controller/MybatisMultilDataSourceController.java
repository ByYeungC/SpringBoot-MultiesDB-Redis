package com.byyeungc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.byyeungc.test01.service.UserServiceTest01;
import com.byyeungc.test02.service.UserServiceTest02;

@RestController
public class MybatisMultilDataSourceController {
	
	@Autowired
	private UserServiceTest01 userServiceTest01;
	@Autowired
	private UserServiceTest02 userServiceTest02;
	
	@RequestMapping("/insertUserTest1")
	public Integer insertUser(String name, Integer age) {
		return userServiceTest01.insertUser(name, age);
		
	}

	
	@RequestMapping("/insertUserTest2")
	public Integer insertUser2(String name, Integer age) {
		return userServiceTest02.insertUser(name, age);
	}
	
	@RequestMapping("/insertUserTest1AndTest2")
	public Integer insertUserTest1AndTest2(String name, Integer age) {
		int i = 1/age;
		return userServiceTest02.insertUserTest01And02(name, age);
		
	}
}
