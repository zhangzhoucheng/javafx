/**
 * com.hpe.bbc.service.cache.service.CacheService.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.commondb.CommonDb;



/**
 * 
 * <note>
 * Desc：初始化cache 字典表数据，获取数据类
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 13:32:17
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 13:32:17    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Service
@CacheConfig(cacheNames="zz-cache")//给改类定义cache name,类下所有方法都会是cacheNames="zz-cache"，除非在方法上进一步定义。
public class DictService {
	@Autowired
	private CommonDb mybatisDao;
	
	/**
	 * Desc:获取指定键的值
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 13:32:51
	 * @modify_record:
	 * @param key
	 * @return
	 */
	@Cacheable(key="#key")
	public String getValueByKey(String key){
		return "";
	}
	
	/**
	 * Desc:删除指定键的值
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 13:33:59
	 * @modify_record:
	 * @param key
	 * @return
	 */
	@CacheEvict(key="#key",allEntries=false)
	public String delValueByKey(String key){
		return "true";
	}
	
	/**
	 * Desc:获取sys_dict通过key-value定义的键值
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 13:34:22
	 * @modify_record:
	 * @return
	 */
	public List<Map<String, String>> getCacheFromKeyValue(){
		return mybatisDao.getList("DictMapper.getCacheFromKeyValue");
	}
	
	/**
	 * Desc:获取sys_dict通过sql定义的键值
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 13:34:33
	 * @modify_record:
	 * @return
	 */
	public List<Map<String, String>> getCacheFromSql(){
		return mybatisDao.getList("DictMapper.getCacheFromSql");
	}
	
	/**
	 * 
	 * <pre>
	 * Desc  执行三期业务库的sql语句
	 * @param sql
	 * @return
	 * @author gaoyang
	 * @date   2017年8月15日 下午4:51:57
	 * </pre>
	 */

	public List<Map<String, String>> getKeyValueFromOracleAudit(String sql){
		return mybatisDao.getList("DictMapper.executeSql",sql);
	}
	/**
	 * 
	 * <pre>
	 * Desc  执行三期源库的sql语句
	 * @param sql
	 * @return
	 * @author gaoyang
	 * @date   2017年8月15日 下午4:57:46
	 * </pre>
	 */

	public List<Map<String, String>> getKeyValueFromOracleMetadata(String sql){
		return mybatisDao.getList("DictMapper.executeSql",sql);
	}
	
	/**
	 * 
	 * <pre>
	 * Desc  执行四期mysql的sql语句
	 * @param sql
	 * @return
	 * @author gaoyang
	 * @date   2017年8月15日 下午5:02:23
	 * </pre>
	 */
	public List<Map<String, String>> getKeyValueFromMysql(String sql){
		return mybatisDao.getList("DictMapper.executeSql",sql);
	}


	@Override
	public String toString() {
		return "zz-cache";
	}
	
}
