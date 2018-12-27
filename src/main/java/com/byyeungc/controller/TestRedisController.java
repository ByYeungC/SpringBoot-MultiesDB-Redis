package com.byyeungc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class TestRedisController {
	@Autowired
    private RedisTemplate<String, String> redisTemplate;
    
    @RequestMapping("/testRedis") 
    public void testRedis() {
    	  redisTemplate.opsForValue().set("TEST", "Test Redis");
//        redisTemplate.opsForValue().set("HELLO", "hello",10, TimeUnit.SECONDS);//设置过时时间
    	  String str = (String) redisTemplate.opsForValue().get("TEST");
//        redisTemplate.opsForValue().getOperations().delete("hello");
        
        log.info(str);
    }
}
