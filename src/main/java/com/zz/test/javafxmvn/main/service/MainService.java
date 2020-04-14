package com.zz.test.javafxmvn.main.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zz.test.javafxmvn.commondb.CommonDb;

/**
 * 
 * <note>
 * Desc： 主方法 service层
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-01-14 17:55:27
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-01-14 17:55:27    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Service
public class MainService {
	@Autowired
	private CommonDb db;

	public void handlerBtnClick() {
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		List<Map<String, Object>> list = db.getList("main.handlerBtnClick");
	}
	
	public List<Map<String, Object>> getProcessList() {
		List<Map<String, Object>> list = db.getList("main.getProcessListNew");
		return list;
	}
}
