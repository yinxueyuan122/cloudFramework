package com.cn.cloud.common.zk.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.cloud.common.zk.app.Application;
import com.cn.cloud.common.zk.util.ZkClient;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ZkListener {

	 @Autowired
	 ZkClient zkClient;
	 
	 NodeCache nodeCache = null;
	
	 @Test
     public void initZk()  {
		 
		 CuratorFramework client = zkClient.getClient();
		 String path = "/yxy";

		 try {
			 nodeCache = new NodeCache(client,path);
			 nodeCache.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        nodeCache.getListenable().addListener(new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
                System.out.println("监听事件触发");
                System.out.println("重新获得节点内容为：" + new String(nodeCache.getCurrentData().getData()));
            }
        });
        try {
        	zkClient.getClient().setData().forPath(path,"123".getBytes());
			zkClient.getClient().setData().forPath(path,"456".getBytes());
			zkClient.getClient().setData().forPath(path,"789".getBytes());
		    zkClient.getClient().setData().forPath(path,"1000".getBytes());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     }
}
