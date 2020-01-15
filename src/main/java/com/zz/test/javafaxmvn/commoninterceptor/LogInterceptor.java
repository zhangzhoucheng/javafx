/**
 * com.hpe.bbc.gateway.filter.LogInterceptor.java Copyright (c) 2017 xx Development Company, L.P. All rights reserved.
 */
package com.zz.test.javafaxmvn.commoninterceptor;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zz.test.javafxmvn.commonbean.BaseObject;
import com.zz.test.javafxmvn.commontool.HttpUtils;
import com.zz.test.javafxmvn.commontool.JsonUtils;



/**
 * <pre>
 * Desc： 
 * &#64;author   peter.fu
 * &#64;date     Jul 31, 2017 6:10:03 PM
 * &#64;version  1.0
 *  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Jul 31, 2017 	   peter.fu 	         1. Created this class.
 * </pre>
 */
public class LogInterceptor extends BaseObject implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

	String traceIdObj = httpServletRequest.getParameter("traceId");
	String traceId = StringUtils.isBlank(traceIdObj) ? UUID.randomUUID().toString() : traceIdObj;

	AccessInfoContextHolder.setTraceId(traceId);
	AccessInfoContextHolder.setReqStartTime(System.currentTimeMillis());
	return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

	AccessInfoContextHolder.setReqEndTime(System.currentTimeMillis());

	logger.info(String.format("@@@ traceId=%s, interval=%s ms, requestInfo=%s, result=%s ", //
		AccessInfoContextHolder.getTraceId(), //
		(AccessInfoContextHolder.getReqEndTime() - AccessInfoContextHolder.getReqStartTime()), //
		HttpUtils.getParameterStr(httpServletRequest), //
		JsonUtils.toJsonStr(AccessInfoContextHolder.getUrlResult())//
	));
    }

}
