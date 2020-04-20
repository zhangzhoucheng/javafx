/**
 * com.hpe.bbc.service.cache.service.LockService.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.commonbean.BaseObject;



/**
 * <pre>
 * Desc： 
 * @author gaoyang
 * @date   2017年9月12日 下午8:54:23
 * @version 1.0
 * @see  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  2017年9月12日 	   gaoyang 	         1. Created this class. 
 * </pre>  
 */
@Service
public class LockService extends BaseObject {
	
	@Autowired
	private RedisService redisService;
	/**锁过期时间,ns纳秒,默认5分钟*/
	private long expireDate = 5*60*1000*1000000;
	/**
	 * 
	 * <pre>
	 * Desc  获取锁
	 * @param key
	 * @return true:获得锁，false:未获得锁
	 * @author gaoyang
	 * @date   2017年9月12日 下午8:56:11
	 * </pre>
	 */
	public boolean getLock(String key){
		Long newDateTimeOne = System.nanoTime();
		boolean isLocked = redisService.setnx(key, String.valueOf(newDateTimeOne));
		//1、数据未上锁，获得锁
		if(isLocked==true){
			return true;
		}
		Object oldDateTimeOneTmp = redisService.get(key);
		if(null==oldDateTimeOneTmp){//防止被无意中删除
			return false;
		}
		Long oldDateTimeOne = Long.parseLong(oldDateTimeOneTmp.toString());
		//2、数据已上锁 并 锁未超时
		if(isLocked==false && newDateTimeOne.longValue()<=(oldDateTimeOne.longValue()+expireDate)){
			return false;
		}
		//3、数据已上锁 并 锁超时
		if(isLocked==false && newDateTimeOne.longValue()>(oldDateTimeOne.longValue()+expireDate)){
			Object oldDateTimeTwoTmp = redisService.getset(key, String.valueOf(newDateTimeOne));
			if(null == oldDateTimeTwoTmp){//防止被无意中删除
				return false;
			}
			Long oldDateTimeTwo = Long.parseLong(oldDateTimeTwoTmp.toString());
			Object newDateTimeTwoTmp = redisService.get(key);
			if(null == newDateTimeTwoTmp){//防止被无意中删除
				return false;
			}
			Long newDateTimeTwo = Long.parseLong(newDateTimeTwoTmp.toString());
			//获得锁的程序，get后的旧时间和设置新时间后返回的旧时间是否相等，get后的新时间和最开始初始化的新时间是否相等，确保获得的锁就是该程序
			if(oldDateTimeOne.longValue()==oldDateTimeTwo.longValue() && newDateTimeOne.longValue()==newDateTimeTwo.longValue()){//获得锁
//				logger.debug("get lock,oldDateTime="+oldDateTime+",oldTmpDate="+oldTmpDate+",newDateTime="+newDateTime+",newTmpDate="+newTmpDate);
				return true;
			}
			if(oldDateTimeOne.longValue()!=oldDateTimeTwo.longValue() || newDateTimeOne.longValue()!=newDateTimeTwo.longValue()){//未获得锁
//				logger.debug("unget lock,oldDateTime="+oldDateTime+",oldTmpDate="+oldTmpDate+",newDateTime="+newDateTime+",newTmpDate="+newTmpDate);
				return false;
			}
		}
		
		return false;
	}
	/**
	 * 
	 * <pre>
	 * Desc  释放锁
	 * @param key
	 * @author gaoyang
	 * @date   2017年9月12日 下午8:57:10
	 * </pre>
	 */
	public void releaseLock(String key){
		redisService.del(key);
	}
	/**
	 * @return the expireDate
	 */
	public long getExpireDate() {
		return expireDate;
	}
	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(long expireDate) {
		this.expireDate = expireDate;
	}
}
