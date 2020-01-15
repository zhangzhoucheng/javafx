package com.zz.test.javafaxmvn.commoninterceptor;

/**
 * <pre>
 * Desc： 
 *  
 * REVISIONS: 
 * Version 	   Date             Author      	  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Sep 9, 2017 	   peter.fu 	         1. Created this class. 
 *
 * </pre>
 * @author peter.fu
 * @date Sep 9, 2017 4:08:11 PM
 * @version 1.0
 */
public abstract class AccessInfoContextHolder {

    public static final ThreadLocal<String> traceId	 = new ThreadLocal<String>();
    public static final ThreadLocal<Long>   reqStartTime = new ThreadLocal<Long>();
    public static final ThreadLocal<Long>   reqEndTime	 = new ThreadLocal<Long>();
    public static final ThreadLocal<Object> urlResult	 = new ThreadLocal<Object>();

    public static void setTraceId(String obj) {
	traceId.set(obj);
    }

    public static String getTraceId() {
	return traceId.get();
    }

    public static void clearTraceId() {
	traceId.remove();
    }

    public static void setReqStartTime(Long obj) {
	reqStartTime.set(obj);
    }

    public static Long getReqStartTime() {
	return reqStartTime.get();
    }

    public static void clearReqStartTime() {
	reqStartTime.remove();
    }

    public static void setReqEndTime(Long obj) {
	reqEndTime.set(obj);
    }

    public static Long getReqEndTime() {
	return reqEndTime.get();
    }

    public static void clearReqEndTime() {
	reqEndTime.remove();
    }

    public static void setUrlResult(Object obj) {
	urlResult.set(obj);
    }

    public static Object getUrlResult() {
	return urlResult.get();
    }

    public static void clearUrlResult() {
	urlResult.remove();
    }
}
