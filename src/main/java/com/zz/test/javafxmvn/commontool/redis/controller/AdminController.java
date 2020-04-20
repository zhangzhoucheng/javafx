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
 * <pre>
 * Desc： 
 * @author gaoyang
 * @date   2017年9月7日 上午11:13:10
 * @version 1.0
 * @see  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  2017年9月7日 	   gaoyang 	         1. Created this class. 
 * </pre>  
 */
@Controller
@RequestMapping("/cache/manage")
public class AdminController extends BaseController {
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request,HttpServletResponse response) {
		return "app/admin/admin";
	}
}
