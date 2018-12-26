package com.byyeungc.test02.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byyeungc.entity.User;
import com.byyeungc.test02.mapper.UserMapperTest02;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceTest02 {
	   @Autowired
	   private UserMapperTest02 users;

	    public List<User> findUserList() {
	    	log.info("#####################all users ############");
	        return users.findAll();
	    }
	    
	    @Transactional(transactionManager = "test2TransactionManager")//不是多数据源不需要指定transactionManager参数，即@Transactional即可
	    public int insertUser(String name, int age) {
	    	int insertUserResult = users.insert(name, age);
	    	log.info("########insertUserResult:{}##");
	    	int i = 1/age;//验证事务开启成功
			return insertUserResult;
	    }

}
