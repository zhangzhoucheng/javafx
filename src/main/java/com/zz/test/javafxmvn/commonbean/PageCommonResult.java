/**
 * com.hpe.bbc.entity.common.PageCommonResult.java Copyright (c) 2017 xx Development Company, L.P. All rights reserved.
 */
package com.zz.test.javafxmvn.commonbean;

/**
 * <pre>
 * Desc： 
 * &#64;author   peter.fu
 * &#64;date     Jul 18, 2017 9:56:55 AM
 * &#64;version  1.0
 *  
 * REVISIONS: 
 * Version 	   Date 		    Author 			  Description
 * ------------------------------------------------------------------- 
 * 1.0 		  Jul 18, 2017 	   peter.fu 	         1. Created this class.
 * </pre>
 */
public class PageCommonResult extends CommonResult {

    private int	 pageNo	      = 1;
    private int	 pageSize     = 10;
    // 总页数
    private long totalPages   = 0;

    // 总行数
    private long totalRecords = 0;
    
    @Override
    public String toString() {
	return "CommonResult [status=" + status + ", message=" + message + ", pageNo=" + pageNo + ", pageSize=" + pageSize + ", totalRecords=" + totalRecords + ", body=" + body + "]";
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

    /**
     * @return the totalPages
     */
    public long getTotalPages() {
	return this.totalPages;
    }

    /**
     * @param totalPages the totalPages to set
     */
    public void setTotalPages(long totalPages) {
	this.totalPages = totalPages;
    }

    /**
     * @return the totalRecords
     */
    public long getTotalRecords() {
	return this.totalRecords;
    }

    /**
     * @param totalRecords the totalRecords to set
     */
    public void setTotalRecords(long totalRecords) {
	this.totalRecords = totalRecords;
	this.totalPages = totalRecords % pageSize == 0 ? totalRecords / pageSize : totalRecords / pageSize + 1;
    }
}
