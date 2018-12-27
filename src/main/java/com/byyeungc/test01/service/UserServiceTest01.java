package com.byyeungc.test01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byyeungc.entity.User;
import com.byyeungc.test01.mapper.UserMapperTest01;

import lombok.extern.slf4j.Slf4j;


@Service
public class UserServiceTest01 {
	   @Autowired
	    private UserMapperTest01 users;

	    public List<User> findUserList() {
	        return users.findAll();
	    }
	    
	    @Transactional()
	    public int insertUser(String name, int age) {
	    	int insertUserResult = users.insert(name, age);
	    	int i = 1/age;//验证事务开启成功 测试方法在26行打断点，进行debug，一步一步走，到27行的时候，insertUserResult已经1，数据库并没有新增
			return insertUserResult;
	    }
}
