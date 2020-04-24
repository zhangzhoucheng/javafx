/**
 * com.hpe.bbc.service.cache.service.RedisService.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
/**
 * 
 * <note>
 * Desc： redis服务,管理查看已有缓存。通过redisTemplate去操作。
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:37:11
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:37:11    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Service
public class RedisService implements InitializingBean {

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	
	private ValueOperations<String, Object> vo;

	/**
	 * Desc:保存key-value
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:37:29
	 * @modify_record:
	 * @param key
	 * @param value
	 */
	public void set(String key, Object value) {
		vo.set(key, value);
	}
	
	/**
	 * Desc:保存key-value
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:38:09
	 * @modify_record:
	 * @param key
	 * @param value
	 * @param time s,>0
	 */
	public void set(String key, Object value,long time){
		vo.set(key, value, time,TimeUnit.SECONDS);
	}
	
	/**
	 * Desc:获取指定key的value
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:38:35
	 * @modify_record:
	 * @param key
	 * @return
	 */
	public Object get(String key) {
		return vo.get(key);
	}
	
	/**
	 * Desc:获取所有key
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:38:43
	 * @modify_record:
	 * @return
	 */
	public List<String> keys(){
		return new ArrayList<String>(vo.getOperations().keys("*"));
	}

	/**
	 * Desc:获取所有key
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:38:52
	 * @modify_record:
	 * @param pattern
	 * @return
	 */
	public List<String> keys(String pattern){
		return new ArrayList<String>(vo.getOperations().keys("*"+pattern+"*"));
	}
	
	/**
	 * Desc:key不存在，保存value，返回true；key存在,不保存value,返回false
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:39:05
	 * @modify_record:
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setnx(String key,Object value){
		return vo.setIfAbsent(key, value);
	}
	
	/**
	 * Desc:设置key-value,并返回旧value
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:39:18
	 * @modify_record:
	 * @param key
	 * @param value
	 * @return
	 */
	public Object getset(String key,Object value){
		return vo.getAndSet(key, value);
	}


	/**
	 * Desc:删除指定的key
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:39:41
	 * @modify_record:
	 * @param key
	 */
	public void del(String key){
		vo.getOperations().delete(key);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		vo = redisTemplate.opsForValue();
	}
}
