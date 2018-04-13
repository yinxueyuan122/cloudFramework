package com.cn.cloud.redis.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cn.cloud")
public class RedisApplication{
	
	
    public static void main(String[] args) {
    	new SpringApplicationBuilder(RedisApplication.class).web(true).run(args);
    }
    
}
