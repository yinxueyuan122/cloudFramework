package com.cn.cloud.common.server.app;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringCloudApplication
@EnableEurekaServer
public class ServerApplication {
	
	 public static void main(String[] args) {
	        new SpringApplicationBuilder(ServerApplication.class)
	                    .web(true).run(args);
	    }
}
