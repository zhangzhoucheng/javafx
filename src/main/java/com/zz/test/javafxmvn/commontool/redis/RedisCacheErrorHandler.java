/**
 * com.hpe.bbc.config.RedisCacheErrorHandler.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.stereotype.Component;

import com.zz.test.javafxmvn.commonbean.BaseObject;


/**
 * 
 * <note>
 * Desc： 操作redis异常配置，捕获从Cache中进行CRUD时的异常的回调处理器
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 09:17:47
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 09:17:47    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Component
public class RedisCacheErrorHandler extends BaseObject implements CacheErrorHandler {

	@Override
	public void handleCacheClearError(RuntimeException arg0, Cache arg1) {
		logger.error("clear cache "+ arg1.getName()+" failed.Errors:"+arg0.getMessage());
	}

	@Override
	public void handleCacheEvictError(RuntimeException arg0, Cache arg1, Object arg2) {
		logger.error("delete key="+arg2.toString()+" from cache "+ arg1.getName()+" failed.Errors:"+arg0.getMessage());
	}

	@Override
	public void handleCacheGetError(RuntimeException arg0, Cache arg1, Object arg2) {
		logger.error("get key="+arg2.toString()+" from cache "+ arg1.getName()+" failed.Errors:"+arg0.getMessage());
	}

	@Override
	public void handleCachePutError(RuntimeException arg0, Cache arg1, Object arg2, Object arg3) {
		logger.error("put key:value="+arg2.toString()+":"+arg3.toString()+" into cache "+ arg1.getName()+" failed.Errors:"+arg0.getMessage());
	}

}
