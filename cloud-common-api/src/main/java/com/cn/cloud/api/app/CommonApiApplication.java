package com.cn.cloud.api.app;

import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringCloudApplication
@ComponentScan(basePackages = "com.cn.cloud")
@EnableFeignClients(basePackages = "com.cn.cloud")
@ImportResource(value = {"classpath*:/spring/applicationContext-*.xml"})
@EnableAutoConfiguration(exclude={  
    	DataSourceAutoConfiguration.class,  
        DataSourceTransactionManagerAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
        }) 
public class CommonApiApplication{
	
	
    public static void main(String[] args) {
    	new SpringApplicationBuilder(CommonApiApplication.class).web(true).run(args);
    }
}
