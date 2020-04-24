package com.zz.test.javafxmvn.commontool.redis.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.commonbean.BaseObject;
import com.zz.test.javafxmvn.commonbean.Constants.ExpireTime;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.commontool.redis.TTLCacheable;

/**
 * 
 * <note>
 * Desc：普通缓存查询，没有则新增 .通过@TTLCacheable，@Cacheable去操作。
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
//@CacheConfig(cacheNames="zz-cache")
public class CacheService extends BaseObject{
	@Autowired
	private CommonDb mybatisDao;
	@Autowired
	private DictService dict;
	@Autowired
	private RedisService re;
	
	@TTLCacheable(type="mainTreeView",key="0",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true, clazzType = ArrayList.class)
	public List<Map<String, String>> queryMainTreeView(String mainTreeView){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mainTreeView", mainTreeView);
		return mybatisDao.getList("DictMapper.getProcessListNew",params);
	}
	
	/**
	 * Desc:测试@Cacheable
	 * //通过原生的缓存获取，根据定义好的KeyGenerator法则 ，这样得到key，和上述@TTLCacheable 类似，但是@TTLCacheable更加灵活。
	   //注意，当MainFxmlView类方法调用queryMainTreeViewBySpring方法时候，由于上下文加载顺序原因，导致@Cacheable不生效。其实完全启动后是没有问题的。
	   //这个问题，耽误了我好多时间，下次注意，
	 * @author jld.zhangzhou
	 * @datetime 2020-04-21 18:05:01
	 * @modify_record:
	 * @param mainTreeView
	 * @return
	 * @throws Exception
	 */
	@Cacheable(value="zz1-cache",keyGenerator="keyGenerator")
	public List<Map<String, String>> queryMainTreeViewBySpring(String mainTreeView) throws Exception{
		
		String v = (String) re.get("zz-cache::cert_type:1");
		String d = dict.getValueByKey("cert_type:1"); 
		String d1 = dict.getValueByKey("mainTreeView:mainTreeView_");
		String v1 = (String) re.get("cert_type:1");
		
		
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mainTreeView", mainTreeView);
		return mybatisDao.getList("DictMapper.getProcessListNew",params);
	}
}
