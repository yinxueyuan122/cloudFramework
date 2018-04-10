package com.cn.cloud.common.file.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.cn.cloud")
public class FileApplication {
	
	 public static void main(String[] args) {
	        new SpringApplicationBuilder(FileApplication.class)
	                    .web(true).run(args);
	    }
}
