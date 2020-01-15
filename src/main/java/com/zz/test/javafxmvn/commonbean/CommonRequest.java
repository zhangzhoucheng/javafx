/**
 * com.hpe.ebtce.core.jackson.CommonParameter.java Copyright (c) 2016 Hewlett-Packard Enterprise Company, L.P. All rights reserved.
 */
package com.zz.test.javafxmvn.commonbean;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * <pre>
 * Desc： 通用请求
 * &#64;author   peter.fu
 * &#64;date     Jul 3, 2017 4:02:11 PM
 * &#64;version  1.0
 *  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Jul 3, 2017 	   peter.fu 	         1. Created this class.
 * </pre>
 */
public class CommonRequest {

    protected String		  traceId = "";

    // 排序字段
    protected String		  orderBy;
    // 排序方式（升序asc或降序desc）
    protected String		  order;

    // 其他的参数我们把它分装成一个Map对象
    protected Map<String, Object> params  = new HashMap<String, Object>();

    public Map<String, Object> getParams() {
	return this.params;
    }

    public void setParams(Map<String, Object> params) {
	this.params = params;
    }

    public void addParameter(String key, Object value) {
	if (this.params == null) {
	    this.params = new HashMap<String, Object>();
	}
	this.params.put(key, value);
    }

    public long getValueAsLong(String key) {
	return (long) params.get(key);
    }

    public double getValueAsDouble(String key) {
	return (double) params.get(key);
    }

    public String getValueAsString(String key) {
	return (String) params.get(key);
    }

    public boolean getValueAsBoolean(String key) {
	return (boolean) params.get(key);
    }

    public long[] getValueAsLongArray(String key) {
	return (long[]) params.get(key);
    }

    public double[] getValueAsDoubleArray(String key) {
	return (double[]) params.get(key);
    }

    public String[] getValueAsStringArray(String key) {
	return (String[]) params.get(key);
    }

    public boolean[] getValueAsBooleanArray(String key) {
	return (boolean[]) params.get(key);
    }

    public Object getValue(String key) {
	return params.get(key);
    }

    /**
     * @return the traceId
     */
    public String getTraceId() {
	return this.traceId;
    }

    /**
     * @param traceId the traceId to set
     */
    public void setTraceId(String traceId) {
	this.traceId = traceId;
    }

    @Override
    public String toString() {

	String s = "";
	for (Entry<String, Object> entry : params.entrySet()) {
	    if (StringUtils.isNotBlank(s))
		s += ", ";

	    Object value = entry.getValue();
	    if (value != null && value.getClass().isArray())
		s += entry.getKey() + "=" + Arrays.toString((Object[]) entry.getValue());
	    else
		s += entry.getKey() + "=" + entry.getValue();

	}
	return "[" + s + "]";
    }


    /**
     * @return the orderBy
     */
    public String getOrderBy() {
	return this.orderBy;
    }

    /**
     * @param orderBy the orderBy to set
     */
    public void setOrderBy(String orderBy) {
	this.orderBy = orderBy;
    }

    /**
     * @return the order
     */
    public String getOrder() {
	return this.order;
    }

    /**
     * @param order the order to set
     */
    public void setOrder(String order) {
	this.order = order;
    }

}
