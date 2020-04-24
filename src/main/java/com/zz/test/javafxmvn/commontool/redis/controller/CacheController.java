/**
 * com.hpe.bbc.service.cache.controller.CacheController.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zz.test.javafxmvn.commonbean.BaseController;
import com.zz.test.javafxmvn.commonbean.CommonResult;
import com.zz.test.javafxmvn.commontool.redis.service.CacheService;
import com.zz.test.javafxmvn.commontool.redis.service.DictInitRunner;
import com.zz.test.javafxmvn.commontool.redis.service.DictService;
import com.zz.test.javafxmvn.commontool.redis.service.RedisService;



/**
 * 
 * <note>
 * Desc：缓存接口 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 10:45:02
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 10:45:02    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@RestController
@RequestMapping("/cache")
public class CacheController extends BaseController {
	@Autowired
	private DictService dictService;

	@Autowired
	private RedisService redisService;

	@Autowired
	private DictInitRunner dictInitRunner;
	
	@Autowired
	private CacheService ca;
	
	@RequestMapping("/get")
	public CommonResult getValueByKey(@RequestParam("key") String key){
		if (StringUtils.isBlank(key)) {
			return getFailCommonResult();
		}
		CommonResult commonResult = getSuccessCommonResult();
		List<String> results = new ArrayList<String>();
		results.add(dictService.getValueByKey(key));
		commonResult.setBody(results);
		return commonResult;
	}
	@RequestMapping("/del")
	public CommonResult delValueByKey(@RequestParam("key") String key){
		if (StringUtils.isBlank(key)) {
			return getFailCommonResult();
		}
		dictService.delValueByKey(key);
		return getSuccessCommonResult();
	}


	/**
	 * Desc:保存key-value
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:44:45
	 * @modify_record:
	 * @param key
	 * @param value
	 * @param time
	 * @return
	 */
	@RequestMapping("/save")
	public CommonResult saveValueByKey(@RequestParam("key") String key,@RequestParam("value") String value, @RequestParam("time") Long time){
		if(StringUtils.isBlank(key) || StringUtils.isBlank(value) || null==time || time<=0){
			return getFailCommonResult();
		}
		redisService.set(key, value, time);
		return getSuccessCommonResult();
	}
	
	/**
	 * Desc:根据opcode获取个人信息
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:42:46
	 * @modify_record:
	 * @param opCode
	 * @return
	 */
/*	@RequestMapping("/auth/queryUserInfoByOpcode")
	public CommonResult queryUserInfoByOpcode(@RequestParam("opCode") String opCode){
		if(StringUtils.isBlank(opCode)){
			return getFailCommonResult();
		}
		CommonResult commonResult = getSuccessCommonResult();
		commonResult.setBody(authService.queryUserInfoByOpcode(opCode));
		return commonResult;
	}*/
	

	/**
	 * Desc:列举所有cache
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:44:34
	 * @modify_record:
	 * @return
	 */
	@RequestMapping("/list")
	public CommonResult list(){
		List<String> keys = redisService.keys();
		Collections.sort(keys);//排序
		List<Map<String, Object>> kvs = new ArrayList<Map<String, Object>>();
		for(String key:keys){
			Map<String, Object> kvmap = new HashMap<String, Object>();
			if(!key.contains("~keys")){
				kvmap.put("key", key);
				kvmap.put("value", redisService.get(key));
			}
			kvs.add(kvmap);
		}
		CommonResult commonResult = getSuccessCommonResult();
		commonResult.setBody(kvs);
		return commonResult;
	}
	
	/**
	 * Desc:获取type下的所有key-value
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:44:27
	 * @modify_record:
	 * @param type
	 * @return
	 */
	@RequestMapping("/getKvsByType")
	public CommonResult getKvsByType(@RequestParam("type") String type){
		if(StringUtils.isBlank(type)){
			return getFailCommonResult();
		}
		List<String> keys = redisService.keys();
		Map<String, Object> kvs = new HashMap<String, Object>();
		for(String key:keys){
			if(key.startsWith(type+":")){
				kvs.put(key, redisService.get(key));
			}
		}
		CommonResult commonResult = getSuccessCommonResult();
		commonResult.setBody(kvs);
		return commonResult;
	}


	/**
	 * Desc:获取包含指定格式的key
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:44:16
	 * @modify_record:
	 * @param pattern
	 * @return
	 */
	@RequestMapping("/getKeysByPattern")
	public CommonResult getKeysByPattern(@RequestParam("pattern") String pattern){
		if(StringUtils.isBlank(pattern)){
			return getFailCommonResult("pattern为null");
		}
		List<String> keys = redisService.keys(pattern);
		
		CommonResult commonResult = getSuccessCommonResult();
		commonResult.setBody(keys);
		return commonResult;
	}
	
	
	/**
	 * Desc:刷新缓存
	 * @author jld.zhangzhou
	 * @datetime 2020-04-17 10:44:06
	 * @modify_record:
	 * @return
	 */
	@RequestMapping("/refresh")
	public CommonResult refresh(){
		try {
			dictInitRunner.run();
			return getSuccessCommonResult();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return getFailCommonResult();
		}
	}
	
	
	@RequestMapping("/test")
	public CommonResult  getM(String menu) throws Exception {
		return new CommonResult(ca.queryMainTreeViewBySpring(menu));
		
	}
	
}
