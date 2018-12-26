package com.byyuengc.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import com.byyeungc.Application;
import com.byyeungc.test02.service.UserServiceTest02;


@RunWith(SpringRunner.class)
@SpringBootTest(classes={Application.class})
public class JunitTest {
	
	@Autowired
	private UserServiceTest02 userServiceTest02;
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
	
	@Test
	public void sayHello() {
		System.out.println("Hello");
	}

	@Test
	public void testAPI() {
		System.out.println(userServiceTest02.insertUser("TestJunit", 44));
	}
	
    @Test
    public void testSet() throws Exception {
    	redisTemplate.opsForValue().set("username", "ByYeungC");
        Assert.assertEquals("ByYeungC", redisTemplate.opsForValue().get("username"));
    }
    
    @Test
	public void testString(){
		//操作String类型的数据
		ValueOperations<String, String> valueStr = redisTemplate.opsForValue();
		//存储一条数据
		valueStr.set("TEST","Test Redis");
		//获取一条数据并输出
		String testStr = valueStr.get("TEST");
		System.out.println(testStr);
		//存储多条数据
		Map<String,String> map = new HashMap<>();
		map.put("id","0");
		map.put("name","ByYeungC");
		map.put("age","1");
		valueStr.multiSet(map);
		
		//获取多条数据
		System.out.println("========================================");
		List<String>list = new ArrayList<>();
		list.add("id");
		list.add("name");
		list.add("age");
 
		List<String> listKeys = valueStr.multiGet(list);
		for (String key : listKeys) {
			System.out.println(key);
		}
 
	}
    
    @Test
	public void testHash(){
		//创建对象
		HashOperations<String, String, String> opsForHash = redisTemplate.opsForHash();
		//存储一条数据
		opsForHash.put("personInfo","id","1");
		//获取一条数据
		String value = opsForHash.get("personInfo", "id");
		System.out.println(value);
 
		//存储多条数据
		Map<String,String> map = new HashMap<>();
		map.put("birthday","1995-11-18");
		map.put("name","ByYeungC");
		opsForHash.putAll("personInfo",map);
		//获取多条数据
		List<String> listKey = new ArrayList<>();
		listKey.add("birthday");
		listKey.add("name");
		List<String> info = opsForHash.multiGet("personInfo", listKey);
		for (String s : info) {
			System.out.println(s);
 
		}
	}

}
