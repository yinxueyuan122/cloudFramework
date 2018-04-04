package com.cn.cloud.user.provider.app;

import org.springframework.boot.actuate.autoconfigure.ManagementWebSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringCloudApplication
@ComponentScan(basePackages = "com.cn.cloud")
@ImportResource(value = {"classpath*:/spring/applicationContext-*.xml"})
@EnableAutoConfiguration(exclude={  
    	DataSourceAutoConfiguration.class,  
        DataSourceTransactionManagerAutoConfiguration.class,
        SecurityAutoConfiguration.class,
        ManagementWebSecurityAutoConfiguration.class
        }) 
public class UserProvider2Application{
	
	
    public static void main(String[] args) {
    	new SpringApplicationBuilder(UserProvider2Application.class).web(true).run(args);
    }
}
