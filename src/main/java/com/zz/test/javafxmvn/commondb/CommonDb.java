package com.zz.test.javafxmvn.commondb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zz.test.javafxmvn.commonbean.CommonRequest;
import com.zz.test.javafxmvn.commonbean.CommonResult;
import com.zz.test.javafxmvn.commonbean.PageCommonRequest;
import com.zz.test.javafxmvn.commonbean.PageCommonResult;
import com.zz.test.javafxmvn.commontool.SqlHelper;


/**
 * 
 * <note>
 * Desc： 公共数据库访问类
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-14 17:37:39
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-14 17:37:39    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
//@Component
public class CommonDb extends SqlSessionDaoSupport{
	@Override
	@Autowired
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	public String getNativeSQL(String key, Object object) {
		return SqlHelper.getNamespaceSql(getSqlSession(), key, object);
	}

	public int add(String key, Object entity) {
		return getSqlSession().insert(key, entity);
	}

	public int delete(String key, Serializable id) {
		return getSqlSession().delete(key, id);
	}

	public int delete(String key, Object object) {
		return getSqlSession().delete(key, object);
	}

	public <T> T get(String key, Object params) {
		T obj = getSqlSession().selectOne(key, params);
		return obj;
	}

	public int update(String key, Object object) {
		return getSqlSession().update(key, object);
	}

	public <T> List<T> getList(String key) {

		long startTime = System.currentTimeMillis();
		List<T> returnList = getSqlSession().selectList(key);
		if (returnList == null) {
			return new ArrayList<T>();
		}
		long endTime = System.currentTimeMillis();
		this.logger.info("@@@ sql=[" + key + "], and time is [" + (endTime - startTime) + "] ms");
		return returnList;
	}

	public <T> List<T> getList(String key, Object params) {

		long startTime = System.currentTimeMillis();
		List<T> returnList = getSqlSession().selectList(key, params);
		if (returnList == null) {
			return new ArrayList<T>();
		}
		long endTime = System.currentTimeMillis();
		this.logger.info("@@@ sql=[" + key + "], and time is [" + (endTime - startTime) + "] ms");
		return returnList;
	}

	public CommonResult getList(String key, CommonRequest commonRequest) {

		long startTime = System.currentTimeMillis();
		try {

			if (commonRequest instanceof PageCommonRequest) {
				PageCommonRequest pageCommonRequest = (PageCommonRequest) commonRequest;
				PageCommonResult pageCommonResult = new PageCommonResult();
				pageCommonResult.setPageNo(pageCommonRequest.getPageNo());
				pageCommonResult.setPageSize(pageCommonRequest.getPageSize());
				PageHelper.startPage(pageCommonRequest.getPageNo(), pageCommonRequest.getPageSize());
				List<Object> returnList = getSqlSession().selectList(key, commonRequest);
				if (returnList == null) {
					pageCommonResult.setBody(new ArrayList<Object>());
					return pageCommonResult;
				}
				PageInfo<Object> pageinfo = new PageInfo<Object>(returnList);
				pageCommonResult.setBody(returnList);
				pageCommonResult.setTotalRecords(pageinfo.getTotal());
				return pageCommonResult;
			}

			CommonResult commonResult = new CommonResult();
			List<Object> returnList = getSqlSession().selectList(key, commonRequest);
			commonResult.setBody(returnList);
			if (returnList == null) {
				commonResult.setBody(new ArrayList<Object>());
			}
			return commonResult;
		} finally {
			long endTime = System.currentTimeMillis();
			this.logger.info("@@@ sql=[" + key + "], and time is [" + (endTime - startTime) + "] ms");
		}

	}

	public int addBatch(String key, List<Object> list) {
		return getSqlSession().insert(key, list);
	}
}
