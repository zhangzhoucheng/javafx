package com.zz.test.javafxmvn.commontool.redis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.commonbean.BaseObject;
import com.zz.test.javafxmvn.commonbean.Constants.ExpireTime;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.commontool.redis.TTLCacheable;

/**
 * 
 * <note>
 * Desc：普通缓存查询，没有则新增 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-17 21:38:18
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-17 21:38:18    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Service
public class CacheService extends BaseObject{
	@Autowired
	private CommonDb mybatisDao;
	
	
	@TTLCacheable(type="mainTreeView",key="0",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true, clazzType = ArrayList.class)
	public List<Map<String, String>> queryMainTreeView(String mainTreeView){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mainTreeView", mainTreeView);
		return mybatisDao.getList("DictMapper.getProcessListNew",params);
	}
	
	@Cacheable//通过原生的缓存获取，与定义好的KeyGenerator法则 ，这样得到key，和上述@TTLCacheable 类似，但是@TTLCacheable更加灵活。
	public List<Map<String, String>> queryMainTreeViewBySpring(String mainTreeView){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mainTreeView", mainTreeView);
		return mybatisDao.getList("DictMapper.getProcessListNew",params);
	}
}
