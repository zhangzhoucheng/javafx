/**
 * com.hpe.bbc.config.RedisCacheResolver.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis;

import java.util.ArrayList;
import java.util.Collection;

import javax.annotation.Resource;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.CacheOperationInvocationContext;
import org.springframework.cache.interceptor.CacheResolver;
import org.springframework.stereotype.Component;

import com.zz.test.javafxmvn.commonbean.BaseObject;



/**
 * 
 * <note>
 * Desc： Cache解析器，用于根据实际情况来动态解析使用哪个Cache
 * 即使配置了CacheResolver，也必须在@CacheConfig或方法上的如@CachePut上指定至少一个Cache Name
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:32:43
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:32:43    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Component
public class RedisCacheResolver extends BaseObject implements CacheResolver {
	
	@Resource
	private CacheManager cacheManager;
	
	@Override
	public Collection<? extends Cache> resolveCaches(CacheOperationInvocationContext<?> arg0) {
		Collection<Cache> caches = new ArrayList<>();
		if (arg0.getTarget().getClass() == null) {
			caches.add(cacheManager.getCache("test-book1"));
			logger.debug("user cache:test-book1");
		}
		if (arg0.getTarget().getClass() == null) {
			caches.add(cacheManager.getCache("test-book"));
			logger.debug("user cache:test-book");
		}
		
        return caches;
	}

}
