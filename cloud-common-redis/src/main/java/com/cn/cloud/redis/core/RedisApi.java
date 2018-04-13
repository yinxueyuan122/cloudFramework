package com.cn.cloud.redis.core;

import com.cn.cloud.redis.util.SerializeUtil;

import redis.clients.jedis.Jedis;


/**
 * 
 * @ClassName   : RedisApi.java
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
public class RedisApi<K,V> {
	
	private RedisFactory redisFactory;
	
	private RedisStringManager<K,V> redisStringManager;

	public RedisFactory getRedisFactory() {
		return redisFactory;
	}

	public void setRedisFactory(RedisFactory redisFactory) {
		this.redisFactory = redisFactory;
	}
	
	public Jedis getJedis(){
		return redisFactory.getPool().getResource();
	}
	
	public void setString(K key,V value){
		getRedisStringManager().set(key, value);
	}
	
	public V getString(K key){
		return getRedisStringManager().get(key);
	}
	
	public void update(K key,V value){
		getRedisStringManager().update(key, value);
	}
	
	private RedisStringManager<K,V>  getRedisStringManager(){
		if (redisStringManager == null) {
			redisStringManager = new CommonRedisStringManager<K, V>(this);
		}
		return redisStringManager;
	}
	
	@SuppressWarnings("unchecked")
	public Long delete(K... keys) {
		final byte[][] rawKeys = new byte[keys.length][];

		int i = 0;
		for (K key : keys) {
			rawKeys[i++] = SerializeUtil.writeToByteArray(key);
		}

		return getJedis().del(rawKeys);
	}

}
