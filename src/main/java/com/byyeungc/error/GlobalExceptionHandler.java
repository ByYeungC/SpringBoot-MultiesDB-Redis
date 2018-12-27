package com.byyeungc.error;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice(basePackages = "com.byyeungc.controller")
public class GlobalExceptionHandler {
	@ExceptionHandler(RuntimeException.class)
	@ResponseBody
	public Map<String, Object> errorResult() {
		Map<String, Object> errorResultMap =  new HashMap<String, Object>();
		errorResultMap.put("errorCode", "500");
		errorResultMap.put("errorMsg",  "AOP全局捕获异常");
		return errorResultMap;
		
	}
}
