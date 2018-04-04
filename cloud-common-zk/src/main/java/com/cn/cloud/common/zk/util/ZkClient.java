package com.cn.cloud.common.zk.util;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;
import org.apache.zookeeper.CreateMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ZkClient {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private CuratorFramework client;
    private String zookeeperServer;
    private int sessionTimeoutMs;
    private int connectionTimeoutMs;
    private int baseSleepTimeMs;
    private int maxRetries;
    private PathChildrenCache cache;

    public void setZookeeperServer(String zookeeperServer) {
        this.zookeeperServer = zookeeperServer;
    }
    public String getZookeeperServer() {
        return zookeeperServer;
    }
    public void setSessionTimeoutMs(int sessionTimeoutMs) {
        this.sessionTimeoutMs = sessionTimeoutMs;
    }
    public int getSessionTimeoutMs() {
        return sessionTimeoutMs;
    }
    public void setConnectionTimeoutMs(int connectionTimeoutMs) {
        this.connectionTimeoutMs = connectionTimeoutMs;
    }
    public int getConnectionTimeoutMs() {
        return connectionTimeoutMs;
    }
    public void setBaseSleepTimeMs(int baseSleepTimeMs) {
        this.baseSleepTimeMs = baseSleepTimeMs;
    }
    public int getBaseSleepTimeMs() {
        return baseSleepTimeMs;
    }
    public void setMaxRetries(int maxRetries) {
        this.maxRetries = maxRetries;
    }
    public int getMaxRetries() {
        return maxRetries;
    }

    public void init() throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(baseSleepTimeMs, maxRetries);
        client = CuratorFrameworkFactory.builder().connectString(zookeeperServer).retryPolicy(retryPolicy)
                .sessionTimeoutMs(sessionTimeoutMs).connectionTimeoutMs(connectionTimeoutMs).build();
        client.start();
        String path = "/yxy";
        cache = new PathChildrenCache(client, path, true);
        cache.start();
		addListener(cache);
    }
    
    private  void addListener(PathChildrenCache cache) 
    { 
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

    public void stop() {
        client.close();
    }

    public CuratorFramework getClient() {
        return client;
    }
    
    public void create(String path,byte[] data){
    	try {
			client.create().creatingParentsIfNeeded().forPath(path,data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public void register() {
        try {
            String rootPath = "/" + "services";
            String hostAddress = InetAddress.getLocalHost().getHostAddress();
            String serviceInstance = "prometheus" + "-" +  hostAddress + "-";
            client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(rootPath + "/" + serviceInstance);
        } catch (Exception e) {
            logger.error("注册出错", e);
        }
    }

    public List<String> getChildren(String path) {
        List<String> childrenList = new ArrayList<>();
        try {
            childrenList = client.getChildren().watched().forPath(path);
        } catch (Exception e) {
            logger.error("获取子节点出错", e);
        }
        return childrenList;
    }
    
    public void delete(String path){
    	try {
			client.delete().forPath(path);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    public int getChildrenCount(String path) {
        return getChildren(path).size();
    }

    public List<String> getInstances() {
        return getChildren("/zk");
    }

    public int getInstancesCount() {
        return getInstances().size();
    }
}
