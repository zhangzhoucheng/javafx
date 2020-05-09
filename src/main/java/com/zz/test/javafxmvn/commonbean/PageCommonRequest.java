/**
 * com.hpe.bbc.entity.common.PageCommonRequest.java Copyright (c) 2017 xx Development Company, L.P. All rights reserved.
 */
package com.zz.test.javafxmvn.commonbean;

/**
 * <pre>
 * Desc： 
 * &#64;author   peter.fu
 * &#64;date     Jul 18, 2017 9:56:40 AM
 * &#64;version  1.0
 *  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Jul 18, 2017 	   peter.fu 	         1. Created this class.
 * </pre>
 */
public class PageCommonRequest extends CommonRequest {

    // 当前页号
    private int	pageNo	 = 1;

    // 每页显示的行数
    private int	pageSize = 10;
    
    public PageCommonRequest() {
    	
    }
    public PageCommonRequest(int pageSize, int pageNo) {
    	this.pageSize = pageSize;
    	this.pageNo = pageNo;
    }

    /**
     * @return the pageNo
     */
    public int getPageNo() {
	return this.pageNo;
    }

    /**
     * @param pageNo the pageNo to set
     */
    public void setPageNo(int pageNo) {
	this.pageNo = pageNo;
    }

    /**
     * @return the pageSize
     */
    public int getPageSize() {
	return this.pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(int pageSize) {
	this.pageSize = pageSize;
    }
}
