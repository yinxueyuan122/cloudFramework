package com.cn.cloud.redis.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.cloud.redis.core.RedisApi;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RedisApplication.class)
public class RedisApiTest {

	@Autowired
	RedisApi<String,Object> redisApi;
	
	 @Test
     public void setString()  {
		 redisApi.setString("ddd", "aaa");
	     System.out.println("ddd"+redisApi.getString("ddd"));
	     
	     redisApi.update("ddd", "bbb");
	     System.out.println("ddd"+redisApi.getString("ddd"));
     }
	 
	 @Test
     public void delString()  {
	     System.out.println("ddd"+redisApi.delete("ddd"));
	     System.out.println("aaa"+redisApi.getString("ddd"));
     }
}
