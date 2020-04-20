/**
 * com.hpe.bbc.config.CacheKeyGenerator.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis;

import java.lang.reflect.Method;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

/**
 * 
 * <note>
 * Desc： 
 * Desc： 默认redis key生成器，这里配置的key生成策略为，key=类的toString()+参数列表
 * 规定：cacheNames=service类的toString()
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:32:01
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:32:01    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
//@Component
public class RedisKeyGenerator implements KeyGenerator {

	@Override
	public Object generate(Object arg0, Method arg1, Object... arg2) {
		StringBuffer stringBuffer = new StringBuffer(arg0.toString()+":");
		for(Object object:arg2){
			stringBuffer.append(String.valueOf(object));
		}
		return stringBuffer.toString();
	}
	

}
