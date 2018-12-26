package com.byyeungc.test02.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.byyeungc.entity.User;


public interface UserMapperTest02 {

	@Select("SELECT * from user")
	 List<User> findAll();
	
	@Insert("insert into user (name, age) values(#{name} , #{age})")
	int insert(@Param("name") String name, @Param("age") int age);
	
	
	
}
