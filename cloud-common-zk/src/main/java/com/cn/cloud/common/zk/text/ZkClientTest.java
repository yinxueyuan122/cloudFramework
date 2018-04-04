package com.cn.cloud.common.zk.text;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.cloud.common.zk.app.Application;
import com.cn.cloud.common.zk.util.ZkClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ZkClientTest {

	 @Autowired
	 ZkClient zkClient;
	
	 @Test
     public void initZk()  {
		 String path = "/yxy";
		 String str = "insert";
		 byte[] bt = null;
		try {
			bt = str.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 zkClient.delete(path);
		 zkClient.create(path,bt);
		 System.out.println(zkClient.getZookeeperServer());
		 try {
			System.out.println(new String(zkClient.getClient().getData().forPath(path)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     }
}
