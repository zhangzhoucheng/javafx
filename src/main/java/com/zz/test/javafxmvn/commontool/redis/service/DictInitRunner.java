/**
 * com.hpe.bbc.service.cache.init.InitRunner.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import com.zz.test.javafxmvn.commonbean.BaseObject;



/**
 * 
 * <note>
 * Desc：  初始化cache 字典表数据，运行类
 * Order(value = 1)控制初始化顺序，value可为2,3,4...
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:36:13
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:36:13    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Component
//@Order(value = 1)
public class DictInitRunner extends BaseObject implements InitializingBean {

	@Autowired
	private DictService dictService;
	@Resource
	private CacheManager cacheManager;
	
	private Cache cache;
	
	
	
	public void run(String... arg0) throws Exception {
		logger.debug("init cache");
		
		//1、初始化sys_dict通过key-value定义的键值
		List<Map<String, String>> keyValueCache = dictService.getCacheFromKeyValue();
		for(Map<String, String> keyValue:keyValueCache){
			cache.put(keyValue.get("type")+":"+keyValue.get("key"), keyValue.get("value"));
		}
		//2、初始化sys_dict通过sql定义的键值
		List<Map<String, String>> sqlCache = dictService.getCacheFromSql();
		for (Map<String, String> sql : sqlCache) {
			if (sql.get("sql_from").equalsIgnoreCase("mysql_audit")) {
				insertCacheFormMysql(sql.get("type"), sql.get("sql_script"));
				continue;
			}
			if (sql.get("sql_from").equalsIgnoreCase("mysql_meta")) {
				insertCacheFormMysql(sql.get("type"), sql.get("sql_script"));
				continue;
			}
			if (sql.get("sql_from").equalsIgnoreCase("oracle_audit")) {
				insertCacheFormOracleAudit(sql.get("type"), sql.get("sql_script"));
				continue;
			}
			if (sql.get("sql_from").equalsIgnoreCase("oracle_meta")) {
				insertCacheFormOracleMeta(sql.get("type"), sql.get("sql_script"));		
			}
		}
		
	}

	/**
	 * 
	 * <pre>
	 * Desc  从四期mysql定义的sql插入cache
	 * @param type 类型
	 * @param results key-value结果集
	 * @author gaoyang
	 * @date   2017年8月15日 下午4:51:01
	 * </pre>
	 */
	private void insertCacheFormMysql(String type,String sqlScripts){
		
		List<Map<String, String>> results = dictService.getKeyValueFromMysql(sqlScripts);
		for(Map<String, String> result:results){
			cache.put(type+":"+result.get("key"), result.get("value"));
		}
		results.clear();
	}
	/**
	 * 
	 * <pre>
	 * Desc  从三期oracle业务库定义的sql插入cache
	 * @param type
	 * @param sqlScripts
	 * @author gaoyang
	 * @date   2017年8月16日 上午10:51:18
	 * </pre>
	 */
	private void insertCacheFormOracleAudit(String type,String sqlScripts){
		
		List<Map<String, String>> results = dictService.getKeyValueFromOracleAudit(sqlScripts);
		for(Map<String, String> result:results){
			cache.put(type+":"+result.get("KEY"), result.get("VALUE"));
		}
		results.clear();
	}
	/**
	 * 
	 * <pre>
	 * Desc  从三期oracle源库定义的sql插入cache
	 * @param type
	 * @param sqlScripts
	 * @author gaoyang
	 * @date   2017年8月16日 上午10:51:57
	 * </pre>
	 */
	private void insertCacheFormOracleMeta(String type,String sqlScripts){
		
		List<Map<String, String>> results = dictService.getKeyValueFromOracleMetadata(sqlScripts);
		for(Map<String, String> result:results){
			cache.put(type+":"+result.get("KEY"), result.get("VALUE"));
		}
		results.clear();
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		cache=cacheManager.getCache(dictService.toString());
		this.run();
	}
}
