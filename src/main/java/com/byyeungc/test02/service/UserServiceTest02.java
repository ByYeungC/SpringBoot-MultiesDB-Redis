package com.byyeungc.test02.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.byyeungc.entity.User;
import com.byyeungc.test01.mapper.UserMapperTest01;
import com.byyeungc.test02.mapper.UserMapperTest02;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceTest02 {
	   @Autowired
	   private UserMapperTest02 users02;
	   @Autowired
	   private UserMapperTest01 users01;

	    public List<User> findUserList() {
	    	log.info("#####################all users ############");
	        return users02.findAll();
	    }
	    
	    @Transactional()//没有使用atomikos,不是多数据源不需要指定transactionManager参数，即@Transactional即可
	    public int insertUser(String name, int age) {
	    	int insertUserResult = users02.insert(name, age);
	    	int i = 1/age;//验证事务开启成功
			return insertUserResult;
	    }
	    
	    @Transactional()//使用atomikos,直接注解即可
	    public int insertUserTest01And02(String name, int age) {
	    	int insertUserResult01 = users01.insert(name, age);//第一个数据源
	    	int insertUserResult02 = users02.insert(name, age);//第二个数据源
	    	int i = 1/age;//验证事务开启成功
	    	int ret = insertUserResult01 + insertUserResult02;
			return ret;
	    }
}
