package com.cn.cloud.redis.app;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.cloud.redis.core.RedisApi;
import com.cn.cloud.redis.model.UserInfoModel;
import com.cn.cloud.redis.util.SerializeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=RedisApplication.class)
public class RedisApiTest {

	@Autowired
	RedisApi<String,Object> redisApi;
	
	 @Test
     public void setString()  {
		 redisApi.setString("ddd", "aaa");
	     System.out.println("ddd"+redisApi.getString("ddd"));
	     
	     redisApi.updateString("ddd", "bbb");
	     System.out.println("ddd"+redisApi.getString("ddd"));
     }
	 
	 @Test
     public void delString()  {
	     System.out.println("ddd"+redisApi.delete("ddd"));
	     System.out.println("aaa"+redisApi.getString("ddd"));
     }
	 
	 
	 @Test
     public void getString()  {
	     System.out.println("byte : "+SerializeUtil.writeToByteArray("UserList"));
     }
	 
	 @Test
     public void zadd()  {
		 Map<String,Object> map1 = new HashMap<String,Object>();
		 map1.put("userId", "CN40798");
		 map1.put("userNm", "yxy1");
		 
		 Map<String,Object> map2 = new HashMap<String,Object>();
		 map2.put("userId", "CN40111");
		 map2.put("userNm", "yxy2");
		 
		 System.out.println("zlist1"+redisApi.zadd("UserList", "123"));
		 System.out.println("zlist2"+redisApi.zadd("UserList", "2234"));
		 
	     System.out.println("zlist"+redisApi.zrange("UserList", 0, 12335).toString());
	     
     }
	 
	 @Test
	 public void zrange()  {
		 UserInfoModel infoModel1 = new UserInfoModel();
		 infoModel1.setId(1);
		 infoModel1.setName("Y1");
		 infoModel1.setTime("20180401");
		 
		 UserInfoModel infoModel2 = new UserInfoModel();
		 infoModel2.setId(2);
		 infoModel2.setName("Y2");
		 infoModel2.setTime("20180402");
		 
		 
		 UserInfoModel infoModel3 = new UserInfoModel();
		 infoModel3.setId(3);
		 infoModel3.setName("Y3");
		 infoModel3.setTime("20180403");
		 
		 UserInfoModel infoModel4 = new UserInfoModel();
		 infoModel4.setId(4);
		 infoModel4.setName("Y4");
		 infoModel4.setTime("20180404");
		 
		 UserInfoModel infoModel5 = new UserInfoModel();
		 infoModel5.setId(5);
		 infoModel5.setName("Y5");
		 infoModel5.setTime("20180405");
		 redisApi.delete("UserRange");
		 redisApi.zaddByScore("UserRange",Long.parseLong(infoModel1.getTime()), infoModel1);
		 redisApi.zaddByScore("UserRange",Long.parseLong(infoModel2.getTime()),  infoModel2);
		 redisApi.zaddByScore("UserRange",Long.parseLong(infoModel3.getTime()),  infoModel3);
		 redisApi.zaddByScore("UserRange",Long.parseLong(infoModel4.getTime()),  infoModel4);
		 redisApi.zaddByScore("UserRange",Long.parseLong(infoModel5.getTime()),  infoModel5);
		 
		 
		 System.out.println("zlist"+redisApi.zrangeByScore("UserRange", 20180402, 20180403).toString());
		 
	 }
}
