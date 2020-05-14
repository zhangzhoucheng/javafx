/**
 * com.hpe.bbc.service.cache.service.Auth.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.commonbean.BaseObject;
import com.zz.test.javafxmvn.commonbean.Constants.ExpireTime;
import com.zz.test.javafxmvn.commondb.CommonDb;
import com.zz.test.javafxmvn.commontool.redis.TTLCacheable;



/**
 * <pre>
 * Desc： 权限相关缓存查询，没有则新增 
 * @author gaoyang
 * @date   2017年8月17日 下午2:29:11
 * @version 1.0
 * @see  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  2017年8月17日 	   gaoyang 	         1. Created this class. 
 * </pre>  
 */
@Service
public class AuthService extends BaseObject{
	@Autowired
	private CommonDb commonDb;
	@Autowired
	private DictService dictService;
	
	@TTLCacheable(type="mainTreeView",key="0",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true, clazzType = ArrayList.class)
	public List<Map<String, String>> queryMainTreeView(String mainTreeView){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("mainTreeView", mainTreeView);
		return commonDb.getList("DictMapper.getProcessListNew",params);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 
	 * <pre>
	 * Desc  根据用户opcode获取权限下的分公司
	 * @param opCode
	 * @return
	 * @author gaoyang
	 * @param menuResourceId 
	 * @date   2017年8月17日 下午6:14:23
	 * </pre>
	 */
	
	@TTLCacheable(type="auth_deptid",key="0,1",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true, clazzType = ArrayList.class)
	public List<Map<String, String>> queryDeptIdList(String opCode, String menuResourceId){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opCode", opCode);
		params.put("menuResourceId", menuResourceId);
		return commonDb.getList("AuthMapper.queryDeptIdList",params);
	}
	/**
	 * 
	 * <pre>
	 * @Title: queryCDeptIdList 
	 * @Description: 根据用户opcode获取权限下的渠道分公司
	 * @param opCode
	 * @return    
	 * @return List<Map<String,String>>    返回类型 
	 * @author debuggao  
	 * @date 2017年12月25日 下午6:59:45
	 * @throws 
	 * </pre>
	 */
	
	@TTLCacheable(type="auth_cdeptid",key="0",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true, clazzType = ArrayList.class)
	public List<Map<String, String>> queryCDeptIdList(String opCode){
		logger.debug("get from oracle audit");
		return commonDb.getList("AuthMapper.queryCDeptIdList",opCode);
	}
	/**
	 * 
	 * <pre>
	 * Desc  根据员工号和分公司查询营业厅
	 * @param opCode
	 * @param deptIds
	 * @return
	 * @author gaoyang
	 * @date   2017年8月17日 下午7:12:34
	 * </pre>
	 */
	
	@TTLCacheable(type="auth_orgid",key="0,1,2",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryOrgIdList(String opCode,String deptL2Ids, String menuResourceId){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opCode", opCode);
		params.put("deptL2Ids", (null!=deptL2Ids&&deptL2Ids!="")?Arrays.asList(deptL2Ids.split(",")):null);
		params.put("menuResourceId", menuResourceId);
		logger.debug(commonDb.getNativeSQL("AuthMapper.queryOrgIdList", params));
		return commonDb.getList("AuthMapper.queryOrgIdList",params);
	}
	/**
	 * 
	 * <pre>
	 * Desc 交互发短信查询有权限的操作员
	 * @param opCode
	 * @param deptIds
	 * @return
	 * @author yanxin
	 * @date   2019年7月2日 
	 * </pre>
	 */
	

	@TTLCacheable(type="auth_orgIds",key="0",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryOpcodeList(String menuResourceId){
		logger.debug("get from oracle audit");
		//Map<String, Object> params = new HashMap<String, Object>();
		//params.put("menuResourceId", menuResourceId);
		logger.debug(commonDb.getNativeSQL("AuthMapper.queryOpcodeList", menuResourceId));
		return commonDb.getList("AuthMapper.queryOpcodeList",menuResourceId);

	}
	/**
	 * 
	 * <pre>
	 * @Title: queryCOrgIdList 
	 * @Description: 根据员工号和渠道分公司查询渠道
	 * @param opCode
	 * @param deptL2Ids
	 * @return    
	 * @return List<Map<String,String>>    返回类型 
	 * @author debuggao  
	 * @date 2017年12月25日 下午7:05:56
	 * @throws 
	 * </pre>
	 */
	
	@TTLCacheable(type="auth_corgid",key="0,1",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryCOrgIdList(String opCode,String deptL2Ids){
		logger.debug("get from oracle audit");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("opCode", opCode);
		if(StringUtils.isBlank(deptL2Ids)){
			params.put("deptL2Ids", null);
		}else{
			params.put("deptL2Ids", Arrays.asList(deptL2Ids.split(",")));
		}
		logger.debug(commonDb.getNativeSQL("AuthMapper.queryCOrgIdList", params));
		return commonDb.getList("AuthMapper.queryCOrgIdList",params);
	}
	/**
	 * 
	 * <pre>
	 * Desc  根据opcode获取个人信息
	 * @param opCode
	 * @return
	 * @author gaoyang
	 * @date   2017年8月22日 上午11:20:55
	 * </pre>
	 */

	@TTLCacheable(type="auth_userinfo",expire=ExpireTime.HALF_A_DAY,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryUserInfoByOpcode(String opCode){
		logger.debug("get from oracle audit");
		return commonDb.getList("AuthMapper.queryUserInfoByOpcode",opCode);
	}
	
	/**
	 * 根据opcode获取用户权限下的资源
	 * @param opCode
	 * @return
	 */
	
	@TTLCacheable(type="auth_res",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<String> queryResDateList(String opCode){
		logger.debug("get from oracle audit");
		List<Map<String, String>> results = commonDb.getList("AuthMapper.queryResDateList",opCode);
		List<String> resList = new ArrayList<String>();
		for(Map<String, String> result:results){
			resList.add(result.get("resId"));
		}
		return resList;
	}
	/**
	 * 根据opcode获取用户权限下的资源的关系树
	 * @param opCode
	 * @return
	 */
	
	@TTLCacheable(type="auth_res_rela",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, Object>> queryResWithRelateDateList(String opCode){
		//权限下0级菜单
		List<Map<String, Object>> menuZero = commonDb.getList("AuthMapper.queryResMenuZeroDateList",opCode);
		//权限下1级菜单
		for(Map<String, Object> zero : menuZero){
			Map<String, String> paramsOne = new HashMap<String, String>();
			paramsOne.put("opCode", opCode);
			paramsOne.put("menuId", (String) zero.get("id"));
			List<Map<String, Object>> menuOne = commonDb.getList("AuthMapper.queryResMenuOneDateList",paramsOne);
			zero.put("arr", menuOne);
			//权限下二级菜单
			for(Map<String, Object> one : menuOne){
				Map<String, String> paramsTwo = new HashMap<String, String>();
				paramsTwo.put("opCode", opCode);
				paramsTwo.put("menuId", (String) one.get("id"));
				List<Map<String, Object>> menuTwo = commonDb.getList("AuthMapper.queryResMenuTwoDateList",paramsTwo);
				for(Map<String, Object> two : menuTwo){
					two.put("sid", getMenuTwoMapRel(two.get("id").toString()));
					two.put("arr", "");
				}
				one.put("arr", menuTwo);
			}
		}
		return menuZero;
	}
	
	/**
	 * 
	 * <pre>
	 * Desc  获取营业厅下的所有员工
	 * @param orgId
	 * @return
	 * @author gaoyang
	 * @date   2017年8月30日 上午10:48:24
	 * </pre>
	 */

	@TTLCacheable(type="queryUserInfoInOrg", expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryUserInfoInOrg(String orgId){
		logger.debug("get from oracle metadata");
		return commonDb.getList("AuthMapper.queryUserInfoInOrg",orgId);
	}
	/**
	 * 
	 * <pre>
	 * Desc  查询个人业务差错类型
	 * @param level high:高风险，normal：中风险，low:低风险，all:全部
	 * @return
	 * @author gaoyang
	 * @date   2017年8月30日 下午3:07:56
	 * </pre>
	 */
	
	@TTLCacheable(type="queryPersonalErrType",expire=ExpireTime.ONE_MON,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryPersonalErrType(String level){
		logger.debug("get from oracle metadata");
		Map<String, String> params = new HashMap<String,String>();
		params.put("level", level);
		logger.debug(commonDb.getNativeSQL("AuthMapper.queryPersonalErrType",params));
		return commonDb.getList("AuthMapper.queryPersonalErrType",params);
	}
	/**
	 * 
	 * <pre>
	 * @Title: queryUserInMenuResources 
	 * @Description: 获取指定菜单下有哪些稽核员
	 * @param resourceId 菜单id
	 * @return
	 * @author debuggao  
	 * @date 2017年12月28日 上午11:00:35
	 * </pre>
	 */
	
	@TTLCacheable(type="queryUserInMenuResources",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryUserInMenuResources(String resourceId){
		logger.debug("get from oracle audit");
		Map<String, String> params = new HashMap<String,String>();
		params.put("resourceId", resourceId);
		logger.debug(commonDb.getNativeSQL("AuthMapper.queryUserInMenuResources",params));
		return commonDb.getList("AuthMapper.queryUserInMenuResources",params);
	}
	
	/**
	 * 
	 * <pre>
	 * 查询某个角色下，有某个营业厅权限的人员
	 * @param roleId 角色id
	 * @param orgId 营业厅id
	 * @return List<Map<String,String>>
	 * @author debuggao  
	 * 2018年1月30日 下午6:14:18
	 * </pre>
	 */
	
	@TTLCacheable(type="queryUserInAuthRoleOrg",key="0,1",expire=ExpireTime.FOUR_HOUR,isRsetTTL=true,clazzType=ArrayList.class)
	public List<Map<String, String>> queryUserInAuthRoleOrg(String roleId,String orgId){
		logger.debug("get from oracle audit");
		Map<String, String> params = new HashMap<String,String>();
		params.put("roleId", roleId);
		params.put("orgId", orgId);
		logger.debug(commonDb.getNativeSQL("AuthMapper.queryUserInAuthRoleOrg",params));
		return commonDb.getList("AuthMapper.queryUserInAuthRoleOrg",params);
	}
	
	/**
	 * 
	 * @Title: getMenuTwoMapRel 
	 * @Description: 通过菜单id获取页面的id，前台展示页面用
	 * @param id
	 * @return    
	 * @return String    返回类型 
	 * @author debuggao  
	 * @throws
	 */
	private String getMenuTwoMapRel(String id){
		return dictService.getValueByKey("func_menu_rela:"+id);
	}
	
	/**
	 * 
	 * <pre>
	 * Desc  查询用户下的角色
	 * @param opCode
	 * @return
	 * </pre>
	 */
	
	public List<Map<String, String>> queryRoleByOpCode(String opCode){
		logger.debug("get from oracle eaudit");
		return commonDb.getList("AuthMapper.queryRoleByOpCode",opCode);
	}
}
