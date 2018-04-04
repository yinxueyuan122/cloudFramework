package com.cn.cloud.common.zk.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.utils.ZKPaths;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cn.cloud.common.zk.app.Application;
import com.cn.cloud.common.zk.util.ZkClient;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=Application.class)
public class ZkPathListener {

	@Autowired
	 ZkClient zkClient;
	 
	 PathChildrenCache   cache = null;
	
	 @Test
    public void initZk()  {
		 
		 CuratorFramework client = zkClient.getClient();
		 String path = "/yxy";
		 try {
			 cache = new PathChildrenCache(client, path, true);
			 addListener(cache);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
	 
	 
	 private  void addListener(PathChildrenCache cache) 
	      { 
	         // a PathChildrenCacheListener is optional. Here, it's used just to log changes 
	         PathChildrenCacheListener listener = new PathChildrenCacheListener() 
	         { 
	             @Override 
	            public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception 
	            { 
	                switch ( event.getType() ) 
	                 { 
	                     case CHILD_ADDED: 
	                     { 
	                        System.out.println("Node added: " + ZKPaths.getNodeFromPath(event.getData().getPath())); 
	                       break; 
	                      } 
	  
	  
	                     case CHILD_UPDATED: 
	                     { 
	                          System.out.println("Node changed: " + ZKPaths.getNodeFromPath(event.getData().getPath())); 
	                         break; 
	                     } 
	 
	  
	                     case CHILD_REMOVED: 
	                     { 
	                          System.out.println("Node removed: " + ZKPaths.getNodeFromPath(event.getData().getPath())); 
	                         break; 
	                     }
					default:
						break; 
	                  } 
	              }

	         }; 
	          cache.getListenable().addListener(listener); 
	      } 

}
