package com.cn.cloud.common.zk.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cn.cloud")
public class Application {
	
     public static void main(String[] args) {
    	new SpringApplicationBuilder(Application.class).web(true).run(args);
     }
    
}
