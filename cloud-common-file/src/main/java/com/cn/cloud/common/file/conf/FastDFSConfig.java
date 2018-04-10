package com.cn.cloud.common.file.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cn.cloud.common.file.client.FastDFSClient;

@Configuration
public class FastDFSConfig {
	
	   @Bean(initMethod = "init", destroyMethod = "stop")
	   public FastDFSClient fastDFSClient() {
		   	FastDFSClient fastDFSClient = new FastDFSClient();
		   	
	        return fastDFSClient;
	   }

}
