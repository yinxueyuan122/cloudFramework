package com.cn.cloud.redis.conf;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.JedisPoolConfig;

import com.cn.cloud.redis.core.RedisApi;
import com.cn.cloud.redis.core.RedisFactory;

/**
 * 
 * @ClassName   : RedisConf.java
 * @Description : 
 * @author Yin Xueyuan
 * @since 2018年4月13日
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2018年4月13日        Yin Xueyuan           fisrt create
 * </pre>
 */
@Configuration
public class RedisConf {
	
	  	@Value("${redis.host}")
	    private String host;

	    @Value("${redis.port}")
	    private int port;

	    @Value("${redis.timeout}")
	    private int timeout;

	    @Value("${redis.pool.max-idle}")
	    private int maxIdle;

	    @Value("${redis.pool.min-idle}") 
	    private int minIdle;
	    
	
	  /**
	   * redis工厂
	   * @Description:
	   * @return
	   */
	  @Bean
	  public RedisFactory redisFactory() {

		RedisFactory factory = new RedisFactory();
	    factory.setHostName(host);
	    factory.setPort(port);
	    //设置连接超时时间
	    factory.setTimeout(timeout); 
	    factory.setUsePool(true);
	    factory.setPoolConfig(jedisPoolConfig());
	    return factory;
	  }
	  
	  
	  /**
	   * redis连接池
	   * @Description:
	   * @return
	   */
	  @Bean
	  public JedisPoolConfig jedisPoolConfig() {
	    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	    jedisPoolConfig.setMaxIdle(maxIdle);
	    jedisPoolConfig.setMinIdle(minIdle);
	    return jedisPoolConfig;
	  }
	  
	  
	  /**
	   * redisApi
	   * @Description:
	   * @param factory
	   * @return
	   */
	  @Bean
	  public RedisApi<String,Object> redisApi() {
		  RedisApi<String,Object> redisApi = new RedisApi<String,Object>();
		  redisApi.setRedisFactory(redisFactory());
	      return redisApi; 
	  }

}
