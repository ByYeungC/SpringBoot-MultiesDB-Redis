package com.byyeungc;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.byyeungc.config.DBConfig1;
import com.byyeungc.config.DBConfig2;


@EnableConfigurationProperties(value = { DBConfig1.class, DBConfig2.class })//开启读取配置文件
@SpringBootApplication
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
