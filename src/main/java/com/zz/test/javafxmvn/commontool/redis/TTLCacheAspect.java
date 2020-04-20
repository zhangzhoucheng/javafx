package com.zz.test.javafxmvn.commontool.redis;


import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;


import com.zz.test.javafxmvn.commonbean.BaseObject;
import com.zz.test.javafxmvn.commontool.JsonUtils;


/**
 * 
 * <note>
 * Desc： aop ,切面编程一个很好的应用，
 * 当访问的方法包括注解@TTLCacheable(type="auth_orgid",key="0,1,2",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
 * 则先进入切面。先看缓存有没，有则刷新时间，且直接返回缓存取到的数据，不走后台service。没有则获取后台service查询到的数据，存入缓存。
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:31:14
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:31:14    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Aspect
@Component
public class TTLCacheAspect extends BaseObject{

	@Resource
	private RedisTemplate<String, Object> redisTemplate;

	@SuppressWarnings({ "unchecked", "unused" })
	@Around("@annotation(cacheable)")//环绕通知，在 注解@annotation(cacheable) （即如下的TTLCacheable），标记的方法环绕通知，
	public Object cacheable(final ProceedingJoinPoint pjp, TTLCacheable cacheable){
		String[] keys = cacheable.key().split(",");
		String key = cacheable.type()+":";//缓存key
		for(String tempKey:keys){
			key=key+pjp.getArgs()[Integer.parseInt(tempKey)].toString()+"_";
		}
		logger.debug("set cache key="+key+", expireTime="+cacheable.expire().getTime());
		
		ValueOperations<String, Object> valueOper = redisTemplate.opsForValue();
		Object value = valueOper.get(key);
		if (value != null) {//缓存有
			if(cacheable.isRsetTTL()==true){
				valueOper.set(key, value, cacheable.expire().getTime(), TimeUnit.SECONDS);//重新刷新ttl时间
			}
			
			return JsonUtils.fromJson(value.toString(), cacheable.clazzType());	
		}
		if(value == null){//缓存没有
			try {
				value = pjp.proceed();
			} catch (Throwable e) {
				logger.error(e.getMessage());
			}
			if (value != null && cacheable.expire().getTime() <= 0) {
				valueOper.set(key, JsonUtils.toJsonStr(value));
			} 
			if (value != null && cacheable.expire().getTime() > 0) {
				valueOper.set(key, JsonUtils.toJsonStr(value), cacheable.expire().getTime(), TimeUnit.SECONDS);
			}
			return value;
		}
		return null;
	}
	

}