/**
 * com.hpe.bbc.service.cache.controller.IndexController.java
 * Copyright (c) 2009 Hewlett-Packard Development Company, L.P.
 * All rights reserved.
 */
package com.zz.test.javafxmvn.commontool.redis.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zz.test.javafxmvn.commonbean.BaseController;


/**
 * 
 * <note>
 * Desc：cache 界面 
 * @author jld.zhangzhou
 * @email idiot_jillidan@163.com;
 * @re be willing to communicate
 * @refactor for jld
 * @datetime 2020-04-21 17:58:11
 * @location mobile base 3th,BeiJing 
 * version  1.0
 *  
 * @REVISIONS: 
 * Version 	        Date 		         Author             Location                   Description          
 * ------------------------------------------------------------------------------------------------------  
 * 1.0 		  2020-04-21 17:58:11    jld.zhangzhou     mobile base 3th,BeiJing      1.create the class            
 * </note>
 */
@Controller
@RequestMapping("/cache/manage")
public class AdminController extends BaseController {
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "app/admin/admin";
	}
}
